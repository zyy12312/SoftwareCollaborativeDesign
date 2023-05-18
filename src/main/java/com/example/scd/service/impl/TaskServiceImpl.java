package com.example.scd.service.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.dao.SubtaskDao;
import com.example.scd.dao.TaskDao;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import com.example.scd.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发表时间是前端传递过来还是后端自己获取
 * @author lain,aya
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;
    @Autowired
    SubtaskDao subtaskDao;
    @Autowired
    SubmissionDao submissionDao;

    @Override
    public Integer createTask(Task task) {
        return taskDao.addTask(task);
    }

    @Override
    public Integer releaseTask(Integer taskId) {
        return taskDao.updateTaskState(taskId);
    }

    @Override
    public Integer modifyTask(Task task) {
        return taskDao.updateTask(task);
    }

    @Override
    public Integer deleteTask(Integer taskId) {
        return taskDao.deleteTask(taskId);
    }

    @Override
    public List<Task> getTaskList() {
        return taskDao.getAllTask();
    }

    @Override
    public List<Task> getAllPublishedTasks() {
        return taskDao.getAllPublishedTask();
    }

    @Override
    public List<Task> getTaskByTitleKey(String titleKey) {
        return taskDao.getTasksByTitle('%'+titleKey+'%');
    }

    @Override
    public Integer createSubTask(Subtask subtask) {
        return subtaskDao.addSubtask(subtask);
    }

    @Override
    public Integer releaseSubtask(Subtask subtask) {
        return subtaskDao.updateSubtask(subtask);
    }

    @Override
    public Integer modifyTask(Subtask task) {
        return subtaskDao.updateSubtask(task);
    }

    @Override
    public Integer deleteSubtask(Integer subtaskId) {
        return subtaskDao.deleteSubtask(subtaskId);
    }

    @Override
    public List<Subtask> getSubtasksOfTask(Integer teamId,Integer taskId) {
        return subtaskDao.getSubTaskList(teamId,taskId);
    }

    @Override
    public Subtask getSubtask(Integer subTaskId) {
        return subtaskDao.getSubTaskDetail(subTaskId);
    }

    @Override
    public Map<String, Long> getStatisticsOfTask() {
        Map<String, Long> taskStatistics = new HashMap<String,Long>();
        Long taskAmount = taskDao.getTaskAmount();
        Long publishedAmount = taskDao.getPublishedAmount();
        Long unpublishedAmount = taskDao.getUnpublishedAmount();
        taskStatistics.put("taskAmount",taskAmount);
        taskStatistics.put("publishedAmount",publishedAmount);
        taskStatistics.put("unpublishedAmount",unpublishedAmount);
        return taskStatistics;
    }

    @Override
    public Integer studentSubmit(Submission submission) {
        return submissionDao.addSubmit(submission);
    }

    @Override
    public Integer deleteSubmit(Integer submitId) {
        return submissionDao.deleteSubmit(submitId);
    }

    @Override
    public List<Submission> getTeamSubmission(Integer teamID, Integer taskID, Integer taskType) {
        return submissionDao.getTeamSubmission(teamID,taskID,taskType);
    }

    @Override
    public List<Submission> getStuSubmission(Integer studentID, Integer taskID, Integer taskType) {
        return null;
    }

    @Override
    public List<Submission> getAllTeamSubmission(Integer taskID, Integer taskType) {
        return submissionDao.getAllStuSubmission(taskID,taskType);
    }

    @Override
    public Integer updateSubmit(Submission submission) {
        return submissionDao.updateSubmit(submission);
    }
}
