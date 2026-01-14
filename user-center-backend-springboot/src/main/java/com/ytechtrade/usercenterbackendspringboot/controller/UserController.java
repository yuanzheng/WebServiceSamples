package com.ytechtrade.usercenterbackendspringboot.controller;

import com.ytechtrade.usercenterbackendspringboot.common.BaseResponse;
import com.ytechtrade.usercenterbackendspringboot.exception.BusinessException;
import com.ytechtrade.usercenterbackendspringboot.exception.ErrorCode;
import com.ytechtrade.usercenterbackendspringboot.model.domain.User;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserDTO;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserLoginRequest;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserRegisterRequest;
import com.ytechtrade.usercenterbackendspringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.ytechtrade.usercenterbackendspringboot.constant.UserConstant.ADMIN_ROLE;
import static com.ytechtrade.usercenterbackendspringboot.constant.UserConstant.USER_LOGIN_STATE;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Request param missing");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        long newUserId = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResponseEntity.ok(new BaseResponse<>(0, newUserId, "ok"));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        log.info("User login now");
        if (userLoginRequest == null) {
            log.error("Login Request is null");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Request param missing");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            log.error("Account or password is empty");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "user account or password is empty");

        }
        return ResponseEntity.ok(userService.userLogin(userAccount, userPassword, request));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser(HttpServletRequest request) {
        UserDTO currentUser = getUserOfRequest(request);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        // TODO 校验用户是否合法
        User user = userService.getById(userId);
        return ResponseEntity.ok(userService.getSafetyUser(user));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(String username, HttpServletRequest request) {
       // 仅管理员可查询
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "Current user does not have the admin privileges");
        }
        return ResponseEntity.ok(userService.searchUsers(username));
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "Current user does not have the admin privileges");
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResponseEntity.ok(userService.removeById(id));
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
        UserDTO user = getUserOfRequest(request);
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

    private static UserDTO getUserOfRequest(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        return (UserDTO) userObj;
    }

    @PostMapping("/logout")
    public ResponseEntity<Integer> userLogout(HttpServletRequest request) {
        log.debug("User logout now");
        if (request == null) {
            log.error("Logout Request is null, logout failed");
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        log.info("Logout user: {}", getUserOfRequest(request).getUserAccount());
        userService.userLogout(request);
        return ResponseEntity.ok(1);
    }

}
