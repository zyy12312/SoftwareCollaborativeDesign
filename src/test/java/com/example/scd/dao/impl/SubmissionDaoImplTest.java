package com.example.scd.dao.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.dao.impl.SubmissionDaoImpl;
import com.example.scd.entity.Submission;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SubmissionDaoImplTest {
    SubmissionDao submissionDao = new SubmissionDaoImpl();

    @Test
    public void getAllGroupGrade() {
        Map<Integer,Double> allGroupGrade = submissionDao.getAllGroupGrade();
        System.out.println(allGroupGrade);
    }

    @Test
    public void addSubmit() {
        Integer result = submissionDao.addSubmit(new Submission(null, 8, 1, "提交了项目简介", null, 2, 0, null, LocalDateTime.now(), null, null));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void deleteSubmit() {
        Integer result = submissionDao.deleteSubmit(2);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void updateSubmit() {
        Integer result = submissionDao.updateSubmit(new Submission(6, 8, 1, "提交了项目简介", null, 2, 0, 90.0, LocalDateTime.now(), "很好", null));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void getSubmissionList() {
        List<Submission> submissionList = submissionDao.getSubmissionList(2, 1);
        System.out.println(submissionList);
    }

    @Test
    public void getTeamSubmission() {
        List<Submission> teamSubmission = submissionDao.getTeamSubmission(1, 2, 1);
        System.out.println(teamSubmission);
    }

    @Test
    public void getStuSubmission() {
        List<Submission> stuSubmission = submissionDao.getStuSubmission(9, 2, 1);
        System.out.println(stuSubmission);
    }
}
