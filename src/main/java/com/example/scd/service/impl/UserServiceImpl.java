package com.example.scd.service.impl;

import com.example.scd.dao.UserDao;
import com.example.scd.entity.User;
import com.example.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllStudent() {
        return userDao.getAllStudent();
    }

    @Override
    public Integer updateStudent(User student) {
        return userDao.updateStudent(student);
    }
}
