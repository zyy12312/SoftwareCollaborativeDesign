package com.example.scd.service;

import com.example.scd.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getNonTeamStudent();
}
