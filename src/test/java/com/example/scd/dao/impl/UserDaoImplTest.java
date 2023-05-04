package com.example.scd.dao.impl;

import com.example.scd.dao.UserDao;
import com.example.scd.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    void getAllStudent(){
        List<User> allStudent = userDao.getAllStudent();
        System.out.println(allStudent);
    }

}
