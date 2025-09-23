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

        User user2 = new User();
        user2.setUserName("test2");
        user2.setUserAccount("dogYupi");
        user2.setAvatarUrl("http");
        user2.setGender(0);
        user2.setUserPassword("12345678");
        user2.setPhone("1234567891111");
        user2.setEmail("dogYupi@gmail.com");
        boolean result2 = userService.save(user2);

        System.out.println("result is " + user2.getId());
        Assertions.assertTrue(result2);
    }


    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "dogYupi";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertNotEquals(-1, result);
    }
}