package com.example.scd.dao.impl;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Submission;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
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

    @Override
    public Integer addSubmit(Submission submission) {
        Integer result = new Integer(0);
        try{
            result = runner.update("insert into Submission(submitterID, teamID, detail, filesURL, targetID, targetType,submitTime) values (?,?,?,?,?,?,?)",
                    submission.getSubmitterID(),submission.getTeamID(),submission.getDetail(),submission.getFileURL(),
                    submission.getTargetID(),submission.getTargetType(),submission.getSubmitTime());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Integer deleteSubmit(Integer submissionId) {
        Integer result = new Integer(0);
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
        Integer result = new Integer(0);
        try{
            result = runner.update("update Submission set submitterID = ?,teamID = ?,detail = ?,filesURL = ?,targetID = ?," +
                            "targetType = ?,submitTime = ?,score = ?,comment = ? where id = ?",
                    submission.getSubmitterID(),submission.getTeamID(),submission.getDetail(),submission.getFileURL(),
                    submission.getTargetID(),submission.getTargetType(),submission.getSubmitTime(),submission.getScore(),
                    submission.getComment(),submission.getSid());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Submission> getSubmissionList(Integer taskID, Integer taskType) {
        try {
            List<Map<String, Object>> mapList = runner.query("select s.submitterID, s.teamID, s.detail, s.filesURL, s.targetID, s.targetType, s.score, s.submitTime, s.comment, s.id sid ,a.* from Submission s," +
                    " Account a where s.submitterID = a.id and s.targetID = ? and s.targetType = ?", new MapListHandler(),taskID,taskType);
            List<Submission> submissionList = new ArrayList<>();
            for (Map<String,Object> map: mapList
            ) {
                Submission submission = new Submission();
                User submitter = new User();
                BeanUtils.populate(submitter,map);
                submission.setSubmitter(submitter);
                BeanUtils.populate(submission,map);
                submissionList.add(submission);
            }
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public List<Submission> getTeamSubmission(Integer teamID, Integer taskID, Integer taskType) {
        try {
            List<Map<String, Object>> mapList = runner.query("select s.submitterID, s.teamID, s.detail, s.filesURL, s.targetID, s.targetType, s.score, s.submitTime, s.comment, s.id sid ,a.* from Submission s," +
                    " Account a where s.submitterID = a.id and s.teamId = ? and s.targetID = ? and s.targetType = ?", new MapListHandler(),teamID,taskID,taskType);
            List<Submission> submissionList = new ArrayList<>();
            for (Map<String,Object> map: mapList
            ) {
                Submission submission = new Submission();
                User submitter = new User();
                BeanUtils.populate(submitter,map);
                submission.setSubmitter(submitter);
                BeanUtils.populate(submission,map);
                submissionList.add(submission);
            }
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public List<Submission> getStuSubmission(Integer studentID, Integer taskID, Integer taskType) {
        try {
            List<Map<String, Object>> mapList = runner.query("select s.submitterID, s.teamID, s.detail, s.filesURL, s.targetID, s.targetType, s.score, s.submitTime, s.comment, s.id sid ,a.* from Submission s," +
                    " Account a where s.submitterID = a.id and s.submitterID = ? and s.targetID = ? and s.targetType = ?", new MapListHandler(),studentID,taskID,taskType);
            List<Submission> submissionList = new ArrayList<>();
            for (Map<String,Object> map: mapList
            ) {
                Submission submission = new Submission();
                User submitter = new User();
                BeanUtils.populate(submitter,map);
                submission.setSubmitter(submitter);
                BeanUtils.populate(submission,map);
                submissionList.add(submission);
            }
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

}
