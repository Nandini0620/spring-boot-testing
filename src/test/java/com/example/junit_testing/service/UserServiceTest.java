package com.example.junit_testing.service;

import com.example.junit_testing.entity.User;
import com.example.junit_testing.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    public void testIsUserRegisterSuccessfully()
    {
        User user=new User();
        user.setEmail("nandini@gmail.com");
       user.setPassword("123456");
        //Optional<User> user= userRepository.findByEmail("nandini@gmail.com");

        when(userRepository.findByEmail("nandini@gmail.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        User added=userService.registerUser(user);
        assertNotNull(added);
        assertEquals("nandini@gmail.com",added.getEmail());
    }

    @Test
    public void testUserNotRegistered()
    {
        User user=new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("123456");
        when(userRepository.findByEmail("nandini@gmail.com")).thenReturn(Optional.of(new User()));
        //User existingUser=userService.registerUser(new User());
       // assertEquals("nandini@gmail.com",existingUser);
        assertThrows(RuntimeException.class,()->userService.registerUser(user));
    }

    @Test
    public void testThrowExceptionWhenDatabaseFail()
    {
        User user=new User();
        user.setEmail("nandini@gmail.com");

        when(userRepository.findByEmail(any())).thenThrow(new RuntimeException("DB connection Fails"));

        assertThrows(RuntimeException.class,()->userService.registerUser(user));
    }

}
