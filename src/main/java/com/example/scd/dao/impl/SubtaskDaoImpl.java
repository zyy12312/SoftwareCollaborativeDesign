package com.example.scd.dao.impl;

import com.example.scd.dao.SubtaskDao;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubtaskDaoImpl implements SubtaskDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public Integer addSubtask(Subtask subtask) {
        try{
            runner.update("insert into Subtask (teamID, characterType, detail, filesURL, endTime, targetID) " +
                    "values (?,?,?,?,?,?)",subtask.getTeamID(),subtask.getCharacterType(),subtask.getDetail(),subtask.getFilesURL(),
                    subtask.getEndTime(),subtask.getTargetID());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public Integer deleteSubtask(Integer subtaskId) {
        try{
            runner.update("delete from Subtask where id = ?",subtaskId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Integer updateSubtask(Subtask subtask) {
        try{
            runner.update("update Subtask set teamID = ?,characterType = ?,detail = ?,filesURL = ?,endTime = ?,targetID = ?" +
                            "where id = ?",subtask.getTeamID(),subtask.getCharacterType(),subtask.getDetail(),subtask.getFilesURL(),
                    subtask.getEndTime(),subtask.getTargetID());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public List<Subtask> getSubTaskList(Integer teamId, Integer taskId) {
        try {
            return runner.query("select s.*,c.`character` from Subtask s,`Character` c where " +
                    "s.characterType = c.id and s.teamID = ? and s.targetID = ?", new BeanListHandler<Subtask>(Subtask.class),teamId,taskId);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Subtask getSubTaskDetail(Integer subTaskId) {
        try {
            return runner.query("select s.*,c.`character` from Subtask s,`Character` c where " +
                    "s.characterType = c.id and s.id = ?", new BeanHandler<Subtask>(Subtask.class),subTaskId);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }
}
