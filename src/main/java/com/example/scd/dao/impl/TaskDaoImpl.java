package com.example.scd.dao.impl;

import com.example.scd.dao.TaskDao;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Task;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.scd.utils.StaticUtil.dateTimeFormatter;
import static com.example.scd.utils.StaticUtil.gson;

@Repository
public class TaskDaoImpl implements TaskDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public Integer addTask(Task task) {
        Integer result = null;
        try{
            result = runner.update("insert into Task (title, detail, endTime, characterType, filesURL,state) values (?,?,?,?,?,?)",
                    task.getTitle(),task.getDetail(),task.getEndTime(),task.getCharacterType(),gson.toJson(task.getFilesURL()),task.getState());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer deleteTask(Integer taskId) {
        Integer result = null;
        try{
            result = runner.update("delete from Task where id = ?",
                    taskId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateTask(Task task) {
        Integer result = null;
        try{
            result = runner.update("update Task set title = ?,detail = ?,endTime = ?,characterType = ?,filesURL = ?,state = ? " +
                            "where id = ?",task.getTitle(),task.getDetail(),task.getEndTime(),task.getCharacterType(),
                    gson.toJson(task.getFilesURL()),task.getState(),task.getId());
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateTaskState(Integer taskId) {
        Integer result = null;
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
                            "where t.characterType = c.id and t.state = 1",
                    new ResultSetHandler<List<Task>>() {
                        @Override
                        public List<Task> handle(ResultSet rs) throws SQLException {
                            List<Task> tasks = new ArrayList<>();
                            Map<Integer, Task> taskMap = new HashMap<>();
                            while (rs.next()) {
                                int taskID = rs.getInt("id");
                                Task task = taskMap.get(taskID);
                                if (task == null) {
                                    task = new Task();
                                    task.setId(taskID);
                                    task.setDetail(rs.getString("detail"));
                                    task.setCharacterType(rs.getInt("characterType"));
                                    task.setTitle(rs.getString("title"));
                                    task.setEndTime(LocalDateTime.parse(rs.getString("endTime"),dateTimeFormatter));
                                    task.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                                    task.setCharacterLabel(rs.getString("character"));
                                    tasks.add(task);
                                    taskMap.put(taskID, task);
                                }
                            }
                            return tasks;
                        }
                    });
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Task> getAllTask() {
        try{
            return runner.query("select t.*,c.`character` characterLabel from Task t,`Character` c " +
                    "where t.characterType = c.id",new ResultSetHandler<List<Task>>() {
                @Override
                public List<Task> handle(ResultSet rs) throws SQLException {
                    List<Task> tasks = new ArrayList<>();
                    Map<Integer, Task> taskMap = new HashMap<>();
                    while (rs.next()) {
                        int taskID = rs.getInt("id");
                        Task task = taskMap.get(taskID);
                        if (task == null) {
                            task = new Task();
                            task.setId(taskID);
                            task.setDetail(rs.getString("detail"));
                            task.setCharacterType(rs.getInt("characterType"));
                            task.setTitle(rs.getString("title"));
                            task.setEndTime(LocalDateTime.parse(rs.getString("endTime"),dateTimeFormatter));
                            task.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                            task.setCharacterLabel(rs.getString("character"));
                            tasks.add(task);
                            taskMap.put(taskID, task);
                        }
                    }
                    return tasks;
                }
            });
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
        Long res = null;
        try{
            res = runner.query("select count(*) from Task",new ScalarHandler<>());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return res;
    }

    @Override
    public Long getUnpublishedAmount() {
        Long res = null;
        try{
            res = runner.query("select count(*) from Task where state = 0",new ScalarHandler<>());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return res;
    }

    @Override
    public Long getPublishedAmount() {
        Long res = null;
        try{
            res = runner.query("select count(*) from Task where state = 1",new ScalarHandler<>());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return res;
    }
}
