package com.example.scd.dao.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SubmissionDaoImpl implements SubmissionDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public List<Map<String,Object>> getAllGroupGrade() {
        try {
            List<Map<String, Object>> groupGradeList = runner.query("select teamID,sum(score) avg from Submission group by teamID", new MapListHandler());
            Number count = runner.query("select count(*) from Task", new ScalarHandler<>());
            for (Map<String,Object> groupGrade:
                 groupGradeList) {
                groupGrade.put("avg",(Double)(groupGrade.get("avg"))/count.intValue());
            }
            return groupGradeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
