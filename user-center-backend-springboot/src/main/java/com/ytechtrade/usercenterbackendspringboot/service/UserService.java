package com.ytechtrade.usercenterbackendspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytechtrade.usercenterbackendspringboot.model.domain.User;

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

}
