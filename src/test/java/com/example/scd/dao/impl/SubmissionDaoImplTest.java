package com.example.scd.dao.impl;

import com.example.scd.dao.SubmissionDao;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionDaoImplTest {
    private SubmissionDao submissionDao = new SubmissionDaoImpl();
    @Test
    void getAllGroupGrade() {
        List<Map<String, Object>> allGroupGrade = submissionDao.getAllGroupGrade();
        System.out.println(allGroupGrade);
    }
}
