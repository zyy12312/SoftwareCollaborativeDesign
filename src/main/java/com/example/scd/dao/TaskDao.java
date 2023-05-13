package com.example.scd.dao;

import com.example.scd.entity.Task;

import java.util.List;

public interface TaskDao {
    Integer addTask(Task task);

    Integer deleteTask(Integer taskId);

    Integer updateTask(Task task);

    List<Task> getAllTask();

    List<Task> getTasksByTitle(String keyTitle);

    Task getTaskDetail(Integer TaskID);

    Long getTaskAmount();

    Long getUnpublishedAmount();

    Long getPublishedAmount();


}
