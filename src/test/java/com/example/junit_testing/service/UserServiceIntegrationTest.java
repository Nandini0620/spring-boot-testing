package com.example.junit_testing.service;

import com.example.junit_testing.entity.User;
import com.example.junit_testing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceIntegrationTest
{
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp()
    {
        userRepository.deleteAll();
    }

    @Test
    public void testIsUserRegisterSuccessfully()
    {
        User user= new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        User result=userService.registerUser(user);

        assertNotNull(result.getId());
        assertEquals("nandini@gmail.com",result.getEmail());
    }

    @Test
    public void testUserAlreadyExsits()
    {
        User existingUser=new User();
        existingUser.setEmail("nandini@gmail.com");
        existingUser.setPassword("1234");

        userRepository.save(existingUser);

        User newUser=new User();
        newUser.setEmail("nandini@gmail.com");
        newUser.setPassword("1234");

        assertThrows(RuntimeException.class,()->userService.registerUser(newUser));
    }

    @Test
    public void testIsUserSavedInDatabase()
    {
        User user = new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("pass123");

        // Act
        userService.registerUser(user);

        // Assert — DB mein directly check karo
        Optional<User> saved = userRepository.findByEmail("nandini@gmail.com");
        assertTrue(saved.isPresent());
        assertEquals("nandini@gmail.com", saved.get().getEmail());
    }
}
