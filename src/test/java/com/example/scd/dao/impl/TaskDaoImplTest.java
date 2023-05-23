package com.example.scd.dao.impl;

import com.example.scd.dao.TaskDao;
import com.example.scd.entity.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskDaoImplTest {

    TaskDao taskDao = new TaskDaoImpl();

    @Test
    public void addTask() {
        Integer result = taskDao.addTask(new Task(null, "需求分析报告", "撰写需求分析报告", LocalDateTime.now(), 2, "产品经理", null, 0));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void deleteTask() {
        Integer result = taskDao.deleteTask(3);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void updateTask() {
        Integer result = taskDao.updateTask(new Task(null, "需求分析报告", "撰写需求分析报告", LocalDateTime.now(), 2, "产品经理", null, 0));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void updateTaskState() {
        Integer result = taskDao.updateTaskState(6);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void getAllPublishedTask() {
        List<Task> allPublishedTask = taskDao.getAllPublishedTask();
        System.out.println(allPublishedTask);
    }

    @Test
    public void getAllTask() {
        List<Task> allTask = taskDao.getAllTask();
        System.out.println(allTask);
    }

    @Test
    public void getTasksByTitle() {
        List<Task> task = taskDao.getTasksByTitle("%需求分析%");
        System.out.println(task);
    }

    @Test
    public void getTaskDetail() {
        Task taskDetail = taskDao.getTaskDetail(3);
        System.out.println(taskDetail);
    }

    @Test
    public void getTaskAmount() {
        Long taskAmount = taskDao.getTaskAmount();
        assertEquals(new Long(2),taskAmount);
    }

    @Test
    public void getUnpublishedAmount() {
        Long unpublishedAmount = taskDao.getUnpublishedAmount();
        assertEquals(new Long(0),unpublishedAmount);
    }

    @Test
    public void getPublishedAmount() {
        Long unpublishedAmount = taskDao.getUnpublishedAmount();
        assertEquals(new Long(2),unpublishedAmount);
    }
}
