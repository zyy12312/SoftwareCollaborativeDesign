package com.example.scd.service.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.entity.User;
import com.example.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AccountDao userDao;

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> getAllStudent() {
        return userDao.getAllStudent();
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateStudent(user);
    }
}
