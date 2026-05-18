package com.example.junit_testing.repository;

import com.example.junit_testing.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest
{
    @Autowired
    UserRepository userRepository;

    @Test
    public void testIsUserSavedSuccessfully()
    {
        User user = new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        User savedUser =userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("nandini@gmail.com",savedUser.getEmail());
    }

    @Test
    public void testFindUserByEmail()
    {
        User user=new User();
        user.setEmail("nandini@gmail.com");
        user.setPassword("1234");

        userRepository.save(user);
        Optional<User> result=userRepository.findByEmail("nandini@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("nandini@gmail.com",result.get().getEmail());
    }

    @Test
    public void testWhenNoEmailFound()
    {
        Optional<User> result=userRepository.findByEmail("usernotexits@gmail.com");

        assertTrue(result.isEmpty());
    }
}
