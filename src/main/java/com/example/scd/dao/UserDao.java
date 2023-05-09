package com.example.scd.dao;

import com.example.scd.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllStudent();
    List<User> getNonTeamStudent();
}
