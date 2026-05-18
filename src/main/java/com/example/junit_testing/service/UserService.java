package com.example.junit_testing.service;

import com.example.junit_testing.entity.User;
import com.example.junit_testing.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user)
    {
      Optional<User> existingUser =  userRepository.findByEmail(user.getEmail());
      if(existingUser.isPresent())
      {
              throw new RuntimeException("user already exists");
      }
      return userRepository.save(user);
    }
}
