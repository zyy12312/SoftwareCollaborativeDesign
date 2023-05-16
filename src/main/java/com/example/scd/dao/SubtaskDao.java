package com.example.scd.dao;

import com.example.scd.entity.Subtask;

import java.util.List;

public interface SubtaskDao {
    Integer addSubtask(Subtask subtask);

//    Integer deleteSubtask(Integer subtaskId, Integer operatorID);
    Integer deleteSubtask(Integer subtaskId);

    Integer updateSubtask(Subtask subTask);

    List<Subtask> getSubTaskList(Integer teamId, Integer taskId);

    Subtask getSubTaskDetail(Integer subTaskID);
}
