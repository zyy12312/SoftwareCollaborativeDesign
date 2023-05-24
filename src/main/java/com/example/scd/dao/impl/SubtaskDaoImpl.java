package com.example.scd.dao.impl;

import com.example.scd.dao.SubtaskDao;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
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
public class SubtaskDaoImpl implements SubtaskDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public Integer addSubtask(Subtask subtask) {
        Integer result = null;
        try{
            result = runner.update("insert into Subtask (teamID, characterType, detail, filesURL, endTime, targetID) " +
                    "values (?,?,?,?,?,?)",subtask.getTeamID(),subtask.getCharacterType(),subtask.getDetail(),subtask.getFilesURL(),
                    subtask.getEndTime(),subtask.getTargetID());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public Integer deleteSubtask(Integer subtaskId) {
        Integer result = null;
        try{
            result = runner.update("delete from Subtask where id = ?",subtaskId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateSubtask(Subtask subtask) {
        Integer result = null;
        try{
            result = runner.update("update Subtask set teamID = ?,characterType = ?,detail = ?,filesURL = ?,endTime = ?,targetID = ? where id = ?",subtask.getTeamID(),subtask.getCharacterType(),subtask.getDetail(),subtask.getFilesURL(),
                    subtask.getEndTime(),subtask.getTargetID(),subtask.getId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Subtask> getSubTaskList(Integer teamId, Integer taskId) {
        try {
            return runner.query("select s.* , c.character characterLabel from Subtask s , `Character` c where s.characterType = c.id and s.teamID = ? and s.targetID = ?",
                    new ResultSetHandler<List<Subtask>>() {
                        @Override
                        public List<Subtask> handle(ResultSet rs) throws SQLException {
                            List<Subtask> subtasks = new ArrayList<>();
                            Map<Integer, Subtask> subtaskMap = new HashMap<>();
                            while (rs.next()) {
                                int subtaskID = rs.getInt("id");
                                Subtask subtask = subtaskMap.get(subtaskID);
                                if (subtask == null) {
                                    subtask = new Subtask();
                                    subtask.setId(subtaskID);
                                    subtask.setDetail(rs.getString("detail"));
                                    subtask.setTargetID(rs.getInt("targetID"));
                                    subtask.setCharacterType(rs.getInt("characterType"));
                                    subtask.setTeamID(rs.getInt("teamID"));
                                    subtask.setEndTime(LocalDateTime.parse(rs.getString("endTime"),dateTimeFormatter));
                                    subtask.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                                    subtask.setCharacterLabel(rs.getString("character"));
                                    subtasks.add(subtask);
                                    subtaskMap.put(subtaskID, subtask);
                                }
                            }
                            return subtasks;
                        }
                    },teamId,taskId);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Subtask getSubTaskDetail(Integer subTaskId) {
        try {
            return runner.query("select s.*,c.`character` characterLabel from Subtask s,`Character` c where " +
                    "s.characterType = c.id and s.id = ?",
                    new ResultSetHandler<Subtask>() {
                        @Override
                        public Subtask handle(ResultSet rs) throws SQLException {

                            int subtaskID = rs.getInt("id");

                            Subtask subtask = new Subtask();
                            subtask.setId(subtaskID);
                            subtask.setDetail(rs.getString("detail"));
                            subtask.setTargetID(rs.getInt("targetID"));
                            subtask.setCharacterType(rs.getInt("characterType"));
                            subtask.setTeamID(rs.getInt("teamID"));
                            subtask.setEndTime(LocalDateTime.parse(rs.getString("endTime"), dateTimeFormatter));
                            subtask.setFilesURL(gson.fromJson(rs.getString("filesURL"), List.class));
                            subtask.setCharacterLabel(rs.getString("character"));

                            return subtask;
                        }
                    }, subTaskId);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }
}
