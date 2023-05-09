package com.example.scd.service.impl;

import com.example.scd.dao.UserDao;
import com.example.scd.entity.User;
import com.example.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public List<User> getNonTeamStudent() {
        return userDao.getNonTeamStudent();
    }
}
