package com.example.scd.dao;

import com.example.scd.entity.Submission;

import java.util.List;
import java.util.Map;

public interface SubmissionDao {
    Map<Integer,Double> getAllGroupGrade();
    Double getOneGroupGrade();
    Integer addSubmit(Submission submission);
    //operatorID为操作者id
//    Integer deleteSubmit(Integer submitId,Integer operatorID);
    Integer deleteSubmit(Integer submissionId);
    Integer updateSubmit(Submission submission);
    //更新成绩
    Integer updateResult(Integer submissionId,Float grade,String comment);
    //查询某作业(作业与子作业)提交情况
    List<Submission> getSubmissionList(Integer taskID , Integer taskType);
    //查询某作业某小组的提交情况
    List<Submission> getTeamSubmission(Integer teamID , Integer taskID , Integer taskType);
    //查询某作业某学生的提交情况
    List<Submission> getStuSubmission(Integer studentID , Integer taskID , Integer taskType);

}
