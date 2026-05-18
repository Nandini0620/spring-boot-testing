package com.example.junit_testing.controller;

import com.example.junit_testing.entity.User;
import com.example.junit_testing.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{

    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(User user)
    {

        return userService.registerUser(user);
    }
}
