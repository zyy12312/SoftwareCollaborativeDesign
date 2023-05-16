package com.example.scd.dao;

import com.example.scd.entity.User;

import java.util.List;

/**
 * 是否缺了一个增加用户功能？
 */
public interface AccountDao {
    List<User> getAllStudent();
    //根据账号查询用户信息
    User getUserByAccount(String account);
    User getUserById(Integer id);
    Integer updateStudent(User student);
//    List<User> getNonTeamStudent();
}
