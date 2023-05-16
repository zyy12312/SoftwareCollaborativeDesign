package com.example.scd.service.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.dao.AccountDao;
import com.example.scd.dao.impl.SubmissionDaoImpl;
import com.example.scd.dao.impl.AccountDaoImpl;
import com.example.scd.entity.Submission;
import com.example.scd.entity.User;
import com.example.scd.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private AccountDao accountDao ;
    @Autowired
    private SubmissionDao submissionDao;

    @Override
    public List<Map<String, Object>> getAllStudentGrade() {
        List<Map<String, Object>> allGroupGrade = submissionDao.getAllGroupGrade();
        List<User> allStudent = accountDao.getAllStudent();
        List<Map<String, Object>> studentGrades = new ArrayList<>();
        for (User u : allStudent){
            studentGrades.add(HashMap.newHashMap(u.getId(),allGroupGrade.))
        }
        return ;
    }

    @Override
    public HashMap<Integer, Double> getGroupGrade(List<Submission> submissionList) {
        return submissionDao.g;
    }

    @Override
    public Integer getStudentGrade(List<User> userList, List<Integer> examScore) {
        return null;
    }
}
