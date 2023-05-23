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
    private AccountDao accountDao;
    @Autowired
    private SubmissionDao submissionDao;

    @Override
    public HashMap<Integer, Map<String, Object>> getAllStudentGrade() {
        Map<Integer, Double> allGroupGrade = submissionDao.getAllGroupGrade();//组号+分数
        List<User> allStudent = accountDao.getAllStudent();
//        List<Map<String, Object>> studentGrades = new ArrayList<>();
        HashMap<Integer, Map<String, Object>> grademap = new HashMap<>();//姓名+（分数项+分数）
        for (User u : allStudent) {
            HashMap<String, Object> userbase = new HashMap<>();
            userbase.put("期末成绩", u.getFinalScore());
            userbase.put("平时成绩", allGroupGrade.get(u.getTeamId()));
            userbase.put("总成绩", u.getFinalScore()*0.5+allGroupGrade.get(u.getTeamId())*0.5);
            userbase.put("学生信息", u);
            grademap.put(u.getId(), userbase);
        }
        return grademap;
    }

    @Override
    public HashMap<Integer, Map<String, Object>> getStuGrade(User student) {
        Double grade = submissionDao.getOneGroupGrade();
//        List<Map<String, Object>> studentGrades = new ArrayList<>();
        HashMap<Integer, Map<String, Object>> grademap = new HashMap<>();//姓名+（分数项+分数）
        HashMap<String, Object> userbase = new HashMap<>();
        userbase.put("期末成绩", student.getFinalScore());
        userbase.put("平时成绩", grade);
        userbase.put("总成绩", student.getFinalScore()*0.5+grade*0.5);
        userbase.put("学生信息", student);
        grademap.put(student.getId(), userbase);
        return grademap;
    }



//    @Override
//    public Integer getStudentGrade(List<User> userList, List<Integer> examScore) {
//        return null;
//    }
}
