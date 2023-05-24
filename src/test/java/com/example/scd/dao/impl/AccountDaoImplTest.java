package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.MaterialDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Material;
import com.example.scd.entity.User;
import com.example.scd.service.impl.GroupingServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoImplTest {
    AccountDao accountDao = new AccountDaoImpl();
    TeamDao teamDao = new TeamDaoImpl();
    @Test
    void getAllStudent() {
        List<User> allStudent = accountDao.getAllStudent();
        System.out.println(allStudent);
    }

    @Test
    void getUserByAccount() {
//        User user = new User(8,"2011110108","e10adc3949ba59abbe56e057f20f883e","学生账号1","https://cos-for-scd-1312783961.cos.ap-shanghai.myqcloud.com/defaultAvator.png",0,0,1,null,true);
        User userByAccount = accountDao.getUserByAccount("2011110108");
//        Assert.assertEquals("user error",user,userByAccount);
    }

    @Test
    void getUserById() {
//        User user = new User(8,"2011110108","e10adc3949ba59abbe56e057f20f883e","学生账号1","https://cos-for-scd-1312783961.cos.ap-shanghai.myqcloud.com/defaultAvator.png",0,0,1,null,true);
        User userById = accountDao.getUserById(8);
//        Assert.assertEquals("user error",user,userById);
    }

    @Test
    void updateStudent() {
//        User user = new User(8,"2011110108","e10adc3949ba59abbe56e057f20f883e","学生账号1","https://cos-for-scd-1312783961.cos.ap-shanghai.myqcloud.com/defaultAvator.png",0,0,1,null,true);
//        Integer integer = accountDao.updateStudent(user);
//        Assert.assertEquals("user update",1,integer.intValue());
    }

    @Test
    void addUser() {
//        User user = new User(null,"2011110109","e10adc3949ba59abbe56e057f20f883e","测试创建账号","https://cos-for-scd-1312783961.cos.ap-shanghai.myqcloud.com/defaultAvator.png",0,0,1,null,true);
//        Integer integer = accountDao.addUser(user);
//        Assert.assertEquals("user create",1,integer.intValue());
    }

    @Test
    void getAllUser() {
        System.out.println(accountDao.getAllUser());

    }


}
