package com.example.scd.dao.impl;

import com.example.scd.dao.TaskDao;
import com.example.scd.entity.Task;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public Integer addTask(Task task) {
        try{
            runner.update("insert into Task (title, detail, endTime, characterType, filesURL,state) values (?,?,?,?,?,?)",
                    task.getTitle(),task.getDetail(),task.getEndTime(),task.getCharacterType(),task.getFileURL(),task.getState());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Integer deleteTask(Integer taskId) {
        try{
            runner.update("delete from Task where id = ?",
                    taskId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Integer updateTask(Task task) {
        Integer result = new Integer(0);
        try{
            result = runner.update("update Task set title = ?,detail = ?,endTime = ?,characterType = ?,filesURL = ?,state = ? " +
                            "where id = ?",task.getTitle(),task.getDetail(),task.getEndTime(),task.getCharacterType(),
                    task.getFileURL(),task.getState(),task.getId());
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateTaskState(Integer taskId) {
        Integer result = new Integer(0);
        try{
            result = runner.update("update Task set state = 1 where id = ?",taskId);
            System.out.println(result);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Task> getAllPublishedTask() {
        try{
            return runner.query("select t.*,c.`character` characterLabel from Task t,`Character` c " +
                    "where t.characterType = c.id and t.state = 1",new BeanListHandler<Task>(Task.class));
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Task> getAllTask() {
        try{
            return runner.query("select t.*,c.`character` characterLabel from Task t,`Character` c " +
                    "where t.characterType = c.id",new BeanListHandler<Task>(Task.class));
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


    @Override
    public List<Task> getTasksByTitle(String keyTitle) {
        try{
            return runner.query("select t.*,c.`character` characterLabel from Task t,`Character` c " +
                    "where t.characterType = c.id and t.title like ?",new BeanListHandler<Task>(Task.class),keyTitle);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Task getTaskDetail(Integer taskId) {
        try{
            return runner.query("select t.*,c.`character` characterLabel from Task t,`Character` c " +
                    "where t.characterType = c.id and t.id = ?",new BeanHandler<Task>(Task.class),taskId);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Long getTaskAmount() {
        try{
            return runner.query("select count(*) from Task",new ScalarHandler<>());
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Long getUnpublishedAmount() {
        try{
            return runner.query("select count(*) from Task where state = 0",new ScalarHandler<>());
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Long getPublishedAmount() {
        try{
            return runner.query("select count(*) from Task where state = 1",new ScalarHandler<>());
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
