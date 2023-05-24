package com.example.scd.dao.impl;

import com.example.scd.dao.SubtaskDao;
import com.example.scd.entity.Subtask;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class SubtaskDaoImplTest {
    SubtaskDao subtaskDao = new SubtaskDaoImpl();
    @Test
    @Rollback
    public void addSubtask() {
        Integer result = subtaskDao.addSubtask(new Subtask(null, 1, 3, "开发经理", "提交各自负责的类图", null, LocalDateTime.now(), 3));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void deleteSubtask() {
        Integer result = subtaskDao.deleteSubtask(6);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void updateSubtask() {
        Integer result = subtaskDao.updateSubtask(new Subtask(8, 1, 5, "测试经理", "提交", null, LocalDateTime.now(), 2));
        assertEquals(new Integer(0),result);
    }

    @Test
    public void getSubTaskList() {
        List<Subtask> subTaskList = subtaskDao.getSubTaskList(1, 2);
        System.out.println(subTaskList);
    }

    @Test
    public void getSubTaskDetail() {
        Subtask subTaskDetail = subtaskDao.getSubTaskDetail(6);
        System.out.println(subTaskDetail);
    }
}
