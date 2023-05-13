package com.example.scd.dao;

import com.example.scd.entity.Submission;

import java.util.List;
import java.util.Map;

public interface SubmissionDao {
    List<Map<String,Object>> getAllGroupGrade();
    Integer addSubmit(Submission submission);
    //operatorID为操作者id
    Integer deleteSubmit(Integer submitId,Integer operatorID);
    Integer updateSubmit(Submission submission);
    //查询某作业所有小组的提交情况
    List<Submission> getTeamSubmissionList(Integer taskID , Integer taskType);
    //查询某作业某小组的提交情况
    Submission getTeamSubmission(Integer teamID , Integer taskID , Integer taskType);
    //查询某子作业某学生的提交情况
    Submission getStuSubmission(Integer studentID , Integer taskID , Integer taskType);
    //查询某子作业所有小组成员的提交情况
    List<Submission> getAllStuSubmission(Integer taskID , Integer taskType);

}
