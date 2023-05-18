package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.service.impl.GroupingServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoImplTest {
    AccountDao accountDao = new AccountDaoImpl();
    TeamDao teamDao = new TeamDaoImpl();
    @Test
    void getAllStudent() {
        System.out.println(accountDao.getAllStudent());
        System.out.println(teamDao.getTeamListByTeamId(1));
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
}
