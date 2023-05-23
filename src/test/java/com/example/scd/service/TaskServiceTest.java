package com.example.scd.service;

import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import com.example.scd.service.impl.TaskServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TaskServiceTest {
    TaskService taskService = new TaskServiceImpl();

    @Test
    public void createTask() {
        Integer result = taskService.createTask(null);
        assertEquals(new Integer(0),result);
    }

    @Test
    public void releaseTask() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(6);
        integers.add(7);
        Integer result = taskService.releaseTask(null);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void modifyTask() {
        //跳过
    }

    @Test
    public void deleteTask() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(8);
        integers.add(9);
        integers.add(10);
        Integer result = taskService.deleteTask(integers);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void getTaskList() {
//        List<Task> taskList = taskService.getTaskList();
//        System.out.println(taskList);
    }

    @Test
    public void getAllPublishedTasks() {
        //
    }

    @Test
    public void getTaskByTitleKey() {
    }

    @Test
    public void getTaskByTaskId() {
        Task taskByTaskId = taskService.getTaskByTaskId(2);
        System.out.println(taskByTaskId);
    }

    @Test
    public void createSubTask() {
        //
    }

    @Test
    public void modifySubtask() {
        //
    }

    @Test
    public void deleteSubtask() {
        //
    }

    @Test
    public void getSubtasksOfTask() {
        List<Subtask> subtasksOfTask = taskService.getSubtasksOfTask(1, 2);
        System.out.println(subtasksOfTask);
    }

    @Test
    public void getSubtask() {
        Subtask subtask = taskService.getSubtask(2);
        System.out.println(subtask);
    }

    @Test
    public void getStatisticsOfTask() {
        Map<String, Long> statisticsOfTask = taskService.getStatisticsOfTask();
        System.out.println(statisticsOfTask);
    }

    @Test
    public void studentSubmit() {
        //
    }

    @Test
    public void modifySubmission() {
        //
    }

    @Test
    public void deleteSubmit() {
        //
    }

    @Test
    public void getTeamSubmission() {
        //
    }

    @Test
    public void getStuSubmission() {
        //
    }

    @Test
    public void getAllTeamSubmission() {
        //
    }

}
