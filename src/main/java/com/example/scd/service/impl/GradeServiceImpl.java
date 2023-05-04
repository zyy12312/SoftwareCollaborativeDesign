package com.example.scd.service.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.dao.UserDao;
import com.example.scd.dao.impl.SubmissionDaoImpl;
import com.example.scd.dao.impl.UserDaoImpl;
import com.example.scd.entity.User;
import com.example.scd.service.GradeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeServiceImpl implements GradeService {
    private UserDao userDao = new UserDaoImpl();
    private SubmissionDao submissionDao = new SubmissionDaoImpl();
    @Override
    public List<Map<String, Object>> getAllStudentGrade() {
        List<User> allStudent = userDao.getAllStudent();
        List<Map<String, Object>> allGroupGrade = submissionDao.getAllGroupGrade();
        List<Map<String, Object>> list = new ArrayList<>();
        return null;
    }
}
