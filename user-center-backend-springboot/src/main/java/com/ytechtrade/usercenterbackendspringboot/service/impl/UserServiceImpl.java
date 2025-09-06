package com.ytechtrade.usercenterbackendspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytechtrade.usercenterbackendspringboot.mapper.UserMapper;
import com.ytechtrade.usercenterbackendspringboot.model.domain.User;
import com.ytechtrade.usercenterbackendspringboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
