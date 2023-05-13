package com.example.scd.service;

import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    //老师创建作业
    Task createTask(Task task);
    //老师发布作业
    Integer releaseTask(Task task);
    //老师修改作业
    Integer modifyTask(Task task);
    //老师删除作业
    Integer deleteTask(Integer taskId);
    //查看所有作业
    List<Task> getTaskList();
    //根据作业名关键词查找作业
    List<Task> getTaskByTitleKey(String titleKey);

    //作业负责人创建子作业
    Subtask createSubTask(Subtask subtask);
    //作业负责人发布子作业
    Integer releaseSubtask(Subtask subtask);
    //作业负责人修改子作业
    Integer modifyTask(Subtask task);
    //作业负责人删除子作业
    Integer deleteSubtask(Integer subtaskId);
    //获取某任务的所有子任务
    List<Subtask> getSubtasksOfTask(Integer taskId);
    //查看具体子作业内容
    Subtask getSubtask(Integer subTaskId);
    //教师页面对作业量进行统计--总数、已发布总数、未发布总数
    Map<String,Long> getStatisticsOfTask();

    //学生提交作业
    Integer studentSubmit(Submission submission);
    //删除提交的作业
    Integer deleteSubmit(Integer submitId);
    //查询某作业某小组的提交结果
    List<Submission> getTeamSubmission(Integer teamID , Integer taskID , Integer taskType);
    //查询某作业某学生的提交结果
    List<Submission>getStuSubmission(Integer studentID , Integer taskID , Integer taskType);
    //查询某作业所有小组的提交结果
    List<Submission> getAllTeamSubmission(Integer taskID , Integer taskType);
    //修改作业提交结果
    Integer updateSubmit(Submission submission);
}
