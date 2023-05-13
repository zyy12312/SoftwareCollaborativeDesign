package com.example.scd.dao.impl;

import com.example.scd.dao.TaskDao;
import com.example.scd.entity.Task;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public Integer addTask(Task task) {
        return null;
    }

    @Override
    public Integer deleteTask(Integer taskId) {
        return null;
    }

    @Override
    public Integer updateTask(Task task) {
        return null;
    }

    @Override
    public List<Task> getAllTask() {
        try{
            return runner.query("select t.*,c.id cid,c.`character` from Task t,`Character` c " +
                    "where t.characterType = c.id",new BeanListHandler<Task>(Task.class));
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Task> getTasksByTitle(String keyTitle) {
        return null;
    }

    @Override
    public Task getTaskDetail(Integer TaskID) {
        return null;
    }

    @Override
    public Long getTaskAmount() {
        return null;
    }

    @Override
    public Long getUnpublishedAmount() {
        return null;
    }

    @Override
    public Long getPublishedAmount() {
        return null;
    }
}
