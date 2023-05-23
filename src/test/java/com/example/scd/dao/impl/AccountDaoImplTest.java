package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.MaterialDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Material;
import com.example.scd.entity.User;
import com.example.scd.service.impl.GroupingServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoImplTest {
    AccountDao accountDao = new AccountDaoImpl();
    TeamDao teamDao = new TeamDaoImpl();
    MaterialDao materialDao = new MaterialDaoImpl();
    @Test
    void getAllStudent() {
        materialDao.updateMaterialState(2);
    }

    @Test
    void getUserByAccount() {
        System.out.println(accountDao.getUserByAccount("2011110101"));
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void addUser() {
    }

    @Test
    void testGetAllStudent() {
    }

    @Test
    void getAllUser() {
    }

    @Test
    void testGetUserByAccount() {
    }

    @Test
    void testGetUserById() {
    }

    @Test
    void testUpdateStudent() {
    }
}
