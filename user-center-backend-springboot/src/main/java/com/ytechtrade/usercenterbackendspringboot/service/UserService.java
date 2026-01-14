package com.ytechtrade.usercenterbackendspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytechtrade.usercenterbackendspringboot.model.domain.User;
import com.ytechtrade.usercenterbackendspringboot.model.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Services for users
 *
 * @since 1.0.0
 * @CreateTime 2025/9/5 11:32
 * @author Yuanzheng Song
 */
public interface UserService extends IService<User> {

    /**
     * New user registration process
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * Login with account and password
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    UserDTO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * Remove all sensitive information from the originUser
     * @param originUser
     * @return
     */
    UserDTO getSafetyUser(User originUser);

    List<UserDTO> searchUsers(String username);

    /**
     * User logout
     *
     * @param request
     * @return
     */
    void userLogout(HttpServletRequest request);
}
