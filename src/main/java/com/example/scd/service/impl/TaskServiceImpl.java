package com.example.scd.service.impl;

import com.example.scd.dao.TaskDao;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import com.example.scd.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;
    @Override
    public Integer createTask(Task task) {
        return null;
    }

    @Override
    public Integer releaseTask(Task task) {
        return null;
    }

    @Override
    public Integer modifyTask(Task task) {
        return null;
    }

    @Override
    public Integer deleteTask(Integer taskId) {
        return null;
    }

    @Override
    public List<Task> getTaskList() {
        return null;
    }

    @Override
    public List<Task> getTaskByTitleKey(String titleKey) {
        return null;
    }

    @Override
    public Subtask createSubTask(Subtask subtask) {
        return null;
    }

    @Override
    public Integer releaseSubtask(Subtask subtask) {
        return null;
    }

    @Override
    public Integer modifyTask(Subtask task) {
        return null;
    }

    @Override
    public Integer deleteSubtask(Integer subtaskId) {
        return null;
    }

    @Override
    public List<Subtask> getSubtasksOfTask(Integer taskId) {
        return null;
    }

    @Override
    public Subtask getSubtask(Integer subTaskId) {
        return null;
    }

    @Override
    public Map<String, Long> getStatisticsOfTask() {
        return null;
    }

    @Override
    public Integer studentSubmit(Submission submission) {
        return null;
    }

    @Override
    public Integer deleteSubmit(Integer submitId) {
        return null;
    }

    @Override
    public List<Submission> getTeamSubmission(Integer teamID, Integer taskID, Integer taskType) {
        return null;
    }

    @Override
    public List<Submission> getStuSubmission(Integer studentID, Integer taskID, Integer taskType) {
        return null;
    }

    @Override
    public List<Submission> getAllTeamSubmission(Integer taskID, Integer taskType) {
        return null;
    }

    @Override
    public Integer updateSubmit(Submission submission) {
        return null;
    }
}
