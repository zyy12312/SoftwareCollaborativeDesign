package com.example.scd.service.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.dao.SubtaskDao;
import com.example.scd.dao.TaskDao;
import com.example.scd.dao.impl.SubtaskDaoImpl;
import com.example.scd.dao.impl.TaskDaoImpl;
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
        if(task != null){
            return taskDao.addTask(task);
        }
        return 0;
    }

    @Override
    public Integer releaseTask(List<Integer> taskIds) {
        if (taskIds != null) {
            Integer result = new Integer(0);
            for (Integer taskId :
                    taskIds) {
                result += taskDao.updateTaskState(taskId);
            }
            if (result == taskIds.size()) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public Integer modifyTask(Task task) {
        if(task != null){
            return taskDao.updateTask(task);
        }
        return 0;
    }

    @Override
    public Integer deleteTask(List<Integer> taskIds) {
        if (taskIds != null) {
            Integer result = new Integer(0);
            for (Integer taskId :
                    taskIds) {
                result += taskDao.deleteTask(taskId);
            }
            if (result == taskIds.size()) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
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
    public Task getTaskByTaskId(Integer taskId) {
        return taskDao.getTaskDetail(taskId);
    }

    @Override
    public Integer createSubTask(List<Subtask> subtasks) {
        if (subtasks != null) {
            Integer result = new Integer(0);
            for (Subtask subtask :
                    subtasks) {
                result += subtaskDao.addSubtask(subtask);
            }
            if (result == subtasks.size()) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public Integer modifySubtask(Subtask subtask) {
        if(subtask != null){
            return subtaskDao.updateSubtask(subtask);
        }
        return 0;
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
        if(submission != null){
            return submissionDao.addSubmit(submission);
        }
        return 0;

    }

    @Override
    public Integer modifySubmission(Submission submission) {
        if(submission != null){
            return submissionDao.updateSubmit(submission);
        }
        return 0;
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
        return submissionDao.getStuSubmission(studentID,taskID,taskType);
    }

    @Override
    public List<Submission> getAllTeamSubmission(Integer taskID, Integer taskType) {
        return submissionDao.getSubmissionList(taskID,taskType);
    }

    @Override
    public Boolean readOver(Integer submissionId, Float grade, String comment) {
        Integer integer = submissionDao.updateResult(submissionId, grade, comment);
        if (integer == null ||integer == 0){
            return false;
        }
        return true;
    }
}
