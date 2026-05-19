package com.example.junit_testing.controller;

import com.example.junit_testing.entity.User;
import com.example.junit_testing.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @Test
    public void testUserRegisterSuccessfully()
    {
        User user = new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        when(userService.registerUser(user)).thenReturn(user);

        User savedUser=userController.registerUser(user);

        assertEquals("nandini@gmail.com",savedUser.getEmail());
    }

    @Test
    public void testRegisterUserServiceCalledOnce()
    {
        User user = new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        when(userService.registerUser(user)).thenReturn(user);
        userController.registerUser(user);

        verify(userService,times(1)).registerUser(user);

    }

    @Test
    public void testRegisterUserNotNull()
    {
        User user = new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        when(userService.registerUser(user)).thenReturn(user);
        User savedUser=userController.registerUser(user);

        assertNotNull(savedUser);
    }

    @Test
    public void testRegisterUserServiceThrowsException()
    {
        User user = new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        when(userService.registerUser(user)).thenThrow(new RuntimeException("exception from service "));

        assertThrows(RuntimeException.class, ()->userController.registerUser(user));

    }

}
