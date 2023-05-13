package com.example.scd.service;

import com.example.scd.entity.Submission;
import com.example.scd.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GradeService {
    /**
     方法名称；getAllStudentGrade
     参数：无
     方法功能：获取所有学生成绩
     返回值：学生成绩列表（List<Map<String,Object>>）
     ？？？
     */
    List<Map<String,Object>> getAllStudentGrade();
    /**
     方法名称；getGroupGrade
     参数：
     @param submissionList：提交记录列表（List<Submission>）
     方法功能：获取小组成绩
     返回值：小组的所有任务成绩列表（HashMap<Integer,Double>）
     */
    HashMap<Integer,Double> getGroupGrade(List<Submission> submissionList);
    /**
     方法名称；getStudentGrade
     参数：
     @param userList：用户列表（List<User>）
     @param examScore：期末成绩列表（List<Integer>）
     方法功能：获取学生成绩
     返回值：成绩（Integer）
     ？？？
     */
    Integer getStudentGrade(List<User> userList, List<Integer> examScore);
}
