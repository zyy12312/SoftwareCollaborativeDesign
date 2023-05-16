package com.example.scd.dao;

import com.example.scd.entity.Task;

import java.util.List;

public interface TaskDao {
    Integer addTask(Task task);

    /**
     方法名称：deleteTask
     参数：taskId--Task的id
     方法功能：删除任务，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer deleteTask(Integer taskId);

    Integer updateTask(Task task);

    List<Task> getAllTask();

    /**
     方法名称：getTasksByTitle
     参数：keyTitle--处理了的关键词，即在两端加了通配符%的字符串
     方法功能：根据标题关键词获取任务，返回获取的任务列表
     返回值：匹配的任务列表
     */
    List<Task> getTasksByTitle(String keyTitle);

    Task getTaskDetail(Integer taskId);

    Long getTaskAmount();

    Long getUnpublishedAmount();

    Long getPublishedAmount();


}
