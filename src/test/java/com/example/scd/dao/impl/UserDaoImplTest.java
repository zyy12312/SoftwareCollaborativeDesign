package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserDaoImplTest {
    private AccountDao userDao = new AccountDaoImpl();
    @Test
    void getAllStudent(){
        List<User> allStudent = userDao.getAllStudent();
        System.out.println(allStudent);
    }

}
