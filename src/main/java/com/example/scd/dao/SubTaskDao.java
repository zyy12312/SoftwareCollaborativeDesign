package com.example.scd.dao;

import com.example.scd.entity.Subtask;

import java.util.List;

public interface SubTaskDao {
    Integer addSubtask(Subtask subtask);

    Integer deleteSubtask(Integer subtaskId, Integer operatorID);

    Integer updateSubtask(Subtask subTask);

    List<Subtask> getSubTaskList(Integer teamId, Integer TaskId);

    List<Subtask> getSubTaskDetail(Integer subTaskID);
}
