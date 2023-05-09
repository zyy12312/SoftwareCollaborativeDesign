package com.example.scd.dao.impl;

import com.example.scd.dao.CharacterDao;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CharacterDaoImpl implements CharacterDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public Integer getNumOfCharacter() {
        try {
            return runner.query("select count(*) from Character where semester = (select max(semester) from Character)",new ScalarHandler<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
