package com.example.scd.dao;

import com.example.scd.entity.Task;

import java.util.List;

/**
 * 将教师添加的作业存储至Task表（addTask(Task task)）、
 * 删除教师要删除的作业（deleteTask(Integer taskId)）、
 * 通过作业标题查找已存在的资料（getTaskByTitle(String title)）、
 * 查询所有作业（getAllTask()）、
 * 查询某一作业的具体信息（+ getTaskDetail(Integer TaskID)）、
 * 对教师修改的作业进行修改（updateTask(Task task)）、
 * 统计作业总数（getTaskAmount()）、已发布总数（getPublishedAmount()）、未发布总数（getUnpublishedAmount()）。
 */
public interface TaskDao {
    List<Task> getAllTask();
    List<Task> getTasksByTitle(String keyTitle);
}
