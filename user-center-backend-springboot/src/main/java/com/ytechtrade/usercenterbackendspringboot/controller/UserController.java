package com.ytechtrade.usercenterbackendspringboot.controller;

import com.ytechtrade.usercenterbackendspringboot.model.domain.User;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserDTO;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserLoginRequest;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserRegisterRequest;
import com.ytechtrade.usercenterbackendspringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ytechtrade.usercenterbackendspringboot.constant.UserConstant.ADMIN_ROLE;
import static com.ytechtrade.usercenterbackendspringboot.constant.UserConstant.USER_LOGIN_STATE;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long newUserId = userService.userRegister(userAccount, userPassword, checkPassword);

        Map<String, Object> response = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        response.put("success", true);
        data.put("status", "ok");
        data.put("currentAuthority", "user");
        if  (newUserId < 0) {
            response.put("success", false);
            data.put("status", "error");
            response.put("errorMessage", "User account is already registered");
        }
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public UserDTO userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        log.info("User login now");
        if (userLoginRequest == null) {
            log.info("Login Request is null");
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            log.info("Account or password is empty");
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @GetMapping("/currentUser")
    public UserDTO getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        UserDTO currentUser = (UserDTO) userObj;
        if (currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        // TODO 校验用户是否合法
        User user = userService.getById(userId);
        return userService.getSafetyUser(user);
    }

    @GetMapping("/search")
    public List<UserDTO> searchUsers(String username, HttpServletRequest request) {
       // 仅管理员可查询
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        return userService.searchUsers(username);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id) {
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        UserDTO user = (UserDTO) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
