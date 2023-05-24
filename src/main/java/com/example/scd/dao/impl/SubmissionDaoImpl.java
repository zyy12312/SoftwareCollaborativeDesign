package com.example.scd.dao.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.scd.utils.StaticUtil.dateTimeFormatter;
import static com.example.scd.utils.StaticUtil.gson;


@Repository
public class SubmissionDaoImpl implements SubmissionDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public Map<Integer, Double> getAllGroupGrade() {
        try {
            List<Map<String, Object>> groupGradeList = runner.query("select teamID,sum(score) avg from Submission group by teamID", new MapListHandler());
            Number count = runner.query("select count(*) from Task", new ScalarHandler<>());
            HashMap<Integer, Double> result = new HashMap<>();
            for (Map<String,Object> groupGrade:
                 groupGradeList) {
                result.put((Integer)groupGrade.get("teamID"),(Double)(groupGrade.get("avg"))/count.intValue());
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getOneGroupGrade() {
        try {
            Number sum = runner.query("select sum(score) avg from Submission where teamID = ?", new ScalarHandler<>());
            Number count = runner.query("select count(*) from Task", new ScalarHandler<>());
            return (sum.doubleValue()/count.intValue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer addSubmit(Submission submission) {
        Integer result = null;
        try{
            result = runner.update("insert into Submission(submitterID, teamID, detail, filesURL, targetID, targetType,submitTime) values (?,?,?,?,?,?,?)",
                    submission.getSubmitterID(),submission.getTeamID(),submission.getDetail(),gson.toJson(submission.getFilesURL()),
                    submission.getTargetID(),submission.getTargetType(),submission.getSubmitTime());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer deleteSubmit(Integer submissionId) {
        Integer result = null;
        try{
            result = runner.update("delete from Submission where id = ?",submissionId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

//    @Override
//    public Integer deleteSubmit(Integer submitId, Integer operatorID) {
//        return null;
//    }

    @Override
    public Integer updateSubmit(Submission submission) {
        Integer result = null;
        try{
            result = runner.update("update Submission set submitterID = ?,teamID = ?,detail = ?,filesURL = ?,targetID = ?," +
                            "targetType = ?,submitTime = ? where id = ?",
                    submission.getSubmitterID(),submission.getTeamID(),submission.getDetail(),gson.toJson(submission.getFilesURL()),
                    submission.getTargetID(),submission.getTargetType(),submission.getSubmitTime(), submission.getId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer updateResult(Integer submissionId, Float grade, String comment) {
        Integer result = null;
        try{
            result = runner.update("update Submission set score = ?,comment = ? where id = ?",
                   grade,comment,submissionId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Submission> getSubmissionList(Integer taskID, Integer taskType) {
        try {
            List<Submission> submissionList  = runner.query("select s.submitterID, s.teamID, s.detail, s.filesURL, s.targetID, s.targetType, s.score, s.submitTime, s.comment, s.id sid ,a.* from Submission s," +
                    " Account a where s.submitterID = a.id and s.targetID = ? and s.targetType = ?",
                    new ResultSetHandler<List<Submission>>() {
                        @Override
                        public List<Submission> handle(ResultSet rs) throws SQLException {
                            List<Submission> submissions = new ArrayList<>();
                            Map<Integer, Submission> submissionMap = new HashMap<>();
                            while (rs.next()) {
                                int submissionId = rs.getInt("sid");
                                Submission submission = submissionMap.get(submissionId);
                                if (submission == null) {
                                    submission = new Submission();
                                    submission.setId(submissionId);
                                    submission.setSubmitterID(rs.getInt("submitterID"));
                                    submission.setTeamID(rs.getInt("teamID"));
                                    submission.setDetail(rs.getString("detail"));
                                    submission.setTargetType(rs.getInt("targetType"));
                                    submission.setTargetID(rs.getInt("targetID"));
                                    submission.setScore(rs.getDouble("score"));
                                    submission.setSubmitTime(LocalDateTime.parse(rs.getString("submitTime"),dateTimeFormatter));
                                    submission.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                                    int userId = rs.getInt("id");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("name"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        submission.setSubmitter(user);
                                    }
                                    submissions.add(submission);
                                    submissionMap.put(submissionId, submission);
                                }
                            }
                            return submissions;
                        }
            }, taskID, taskType);
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public List<Submission> getTeamSubmission(Integer teamID, Integer taskID, Integer taskType) {
        try {
            List<Submission> submissionList = runner.query("select s.submitterID, s.teamID, s.detail, s.filesURL, s.targetID, s.targetType, s.score, s.submitTime, s.comment, s.id sid ,a.* from Submission s," +
                    " Account a where s.submitterID = a.id and s.teamId = ? and s.targetID = ? and s.targetType = ?",
                    new ResultSetHandler<List<Submission>>() {
                        @Override
                        public List<Submission> handle(ResultSet rs) throws SQLException {
                            List<Submission> submissions = new ArrayList<>();
                            Map<Integer, Submission> submissionMap = new HashMap<>();
                            while (rs.next()) {
                                int submissionId = rs.getInt("sid");
                                Submission submission = submissionMap.get(submissionId);
                                if (submission == null) {
                                    submission = new Submission();
                                    submission.setId(submissionId);
                                    submission.setSubmitterID(rs.getInt("submitterID"));
                                    submission.setTeamID(rs.getInt("teamID"));
                                    submission.setDetail(rs.getString("detail"));
                                    submission.setTargetType(rs.getInt("targetType"));
                                    submission.setTargetID(rs.getInt("targetID"));
                                    submission.setScore(rs.getDouble("score"));
                                    submission.setSubmitTime(LocalDateTime.parse(rs.getString("submitTime"),dateTimeFormatter));
                                    submission.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                                    int userId = rs.getInt("id");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("name"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        submission.setSubmitter(user);
                                    }
//                                    User user = new User();
//                                    team.setStudent(user);
                                    submissions.add(submission);
                                    submissionMap.put(submissionId, submission);
                                }
                            }
                            return submissions;
                        }
                    },teamID,taskID,taskType);
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public List<Submission> getStuSubmission(Integer studentID, Integer taskID, Integer taskType) {
        try {
            List<Submission> submissionList = runner.query("select s.submitterID, s.teamID, s.detail, s.filesURL, s.targetID, s.targetType, s.score, s.submitTime, s.comment, s.id sid ,a.* from Submission s," +
                    " Account a where s.submitterID = a.id and s.submitterID = ? and s.targetID = ? and s.targetType = ?",
                    new ResultSetHandler<List<Submission>>() {
                        @Override
                        public List<Submission> handle(ResultSet rs) throws SQLException {
                            List<Submission> submissions = new ArrayList<>();
                            Map<Integer, Submission> submissionMap = new HashMap<>();
                            while (rs.next()) {
                                int submissionId = rs.getInt("sid");
                                Submission submission = submissionMap.get(submissionId);
                                if (submission == null) {
                                    submission = new Submission();
                                    submission.setId(submissionId);
                                    submission.setSubmitterID(rs.getInt("submitterID"));
                                    submission.setTeamID(rs.getInt("teamID"));
                                    submission.setDetail(rs.getString("detail"));
                                    submission.setTargetType(rs.getInt("targetType"));
                                    submission.setTargetID(rs.getInt("targetID"));
                                    submission.setScore(rs.getDouble("score"));
                                    submission.setSubmitTime(LocalDateTime.parse(rs.getString("submitTime"),dateTimeFormatter));
                                    submission.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                                    int userId = rs.getInt("id");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("name"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        submission.setSubmitter(user);
                                    }
//                                    User user = new User();
//                                    team.setStudent(user);
                                    submissions.add(submission);
                                    submissionMap.put(submissionId, submission);
                                }


                            }
                            return submissions;
                        }
                    },studentID,taskID,taskType);
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

}
