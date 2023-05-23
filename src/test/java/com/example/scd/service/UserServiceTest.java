package com.example.scd.service;

import com.example.scd.entity.User;
import com.example.scd.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void addUser() {
    }

    @Test
    public void getUserById() {
        User userById = userService.getUserById(8);
        System.out.println(userById);
    }

    @Test
    public void getAllStudent() {
        List<User> allStudent = userService.getAllStudent();
        System.out.println(allStudent);
    }

    @Test
    public void getAllUser() {
    }

    @Test
    public void updateUser() {
    }
}
