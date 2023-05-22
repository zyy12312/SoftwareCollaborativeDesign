package com.example.scd.service;

import com.example.scd.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Integer addUser(User user);
    User getUserById(Integer id);
    List<User> getAllStudent();
    List<User> getAllUser();
    Integer updateUser(User user);
}
