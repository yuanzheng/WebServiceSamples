package com.ytechtrade.usercenterbackendspringboot.service;

import com.ytechtrade.usercenterbackendspringboot.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("lucas song");
        user.setUserAccount("lucassong");
        user.setAvatarUrl("http");
        user.setGender(0);
        user.setUserPassword("abc");
        user.setPhone("123456789");
        user.setEmail("song2680@gmail.com");
        boolean result = userService.save(user);

        System.out.println("result is " + user.getId());
        Assertions.assertTrue(result);
    }


}