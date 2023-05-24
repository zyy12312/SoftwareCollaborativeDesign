package com.example.scd.dao.impl;

import com.example.scd.dao.ReplyDao;
import com.example.scd.entity.Message;
import com.example.scd.entity.Reply;
import com.example.scd.entity.Submission;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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

/**
 * 读取回复列表未完成
 */
@Repository
public class ReplyDaoImpl implements ReplyDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public List<Reply> getReplyList(Integer discussID) {
        try {
            return runner.query("select r.*,a.id uid,a.name uname,a.account,a.sex,a.role,a.avatarURL  from Reply r , Account a where replyTarget = ? and replyIsDiscuss = 0",
                    new ResultSetHandler<List<Reply>>() {
                        @Override
                        public List<Reply> handle(ResultSet rs) throws SQLException {
                            List<Reply> replies = new ArrayList<>();
                            Map<Integer, Reply> replyMap = new HashMap<>();
                            while (rs.next()) {
                                int submissionId = rs.getInt("id");
                                Reply reply = replyMap.get(submissionId);
                                if (reply == null) {
                                    reply = new Reply();
                                    reply.setId(rs.getInt("id"));
                                    reply.setReplyTarget(rs.getInt("replyTarget"));
                                    reply.setReplyIsDiscuss(rs.getInt("replyIsDiscuss"));
                                    reply.setReplyTime(LocalDateTime.parse(rs.getString("replyTime"),dateTimeFormatter));
                                    reply.setDetail(rs.getString("detail"));
                                    reply.setFilesURL(gson.fromJson(rs.getString("filesURL"),List.class));
                                    reply.setAuthorID(rs.getInt("authorID"));
                                    int userId = rs.getInt("uid");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("uname"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        reply.setAuthor(user);
                                    }
                                    replies.add(reply);
                                    replyMap.put(submissionId, reply);
                                }
                            }
                            return replies;
                        }
                    }, discussID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer deleteReplyByDiscussID(Integer discussId) {
        Integer res = null;
        try {
            res = runner.update("delete from Reply where replyIsDiscuss=0 and replyTarget = ?",discussId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer deleteReplyByReplyID(Integer replyId) {
        Integer res = null;
        try {
            res =  runner.update("delete from Reply where replyIsDiscuss=1 and replyTarget = ?",replyId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer addNewReply(Reply reply) {
        Integer res = null;
        try{
            res = runner.update("insert into Reply (detail, filesURL, authorID, replyTarget, replyIsDiscuss, replyTime) " +
                    "values (?,?,?,?,?,?)",reply.getDetail(),gson.toJson(reply.getFilesURL()),reply.getAuthorID(),reply.getReplyTarget(),
                    reply.getReplyIsDiscuss(),reply.getReplyTime());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return res;
    }

//    @Override
//    public Integer addNewReply(Integer authorID, Integer id) {
//        return null;
//    }

    @Override
    public Integer deleteReply( Integer id) {
        Integer res = null;
        try{
            res = runner.update("delete from Reply where id = ?",id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer modifyReply(Reply reply) {
        Integer res = null;
        try{
            res = runner.update("update Reply set detail = ?,filesURL = ?,authorID = ?,replyTarget = ?,replyIsDiscuss = ?,replyTime = ?" +
                            "where id = ?",reply.getDetail(),gson.toJson(reply.getFilesURL()),reply.getAuthorID(),reply.getReplyTarget(),
                    reply.getReplyIsDiscuss(),reply.getReplyTime(),reply.getId());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return res;
    }

    @Override
    public Integer addNewReplyList(List<Reply> replyList) {
        return null;
    }

    @Override
    public Reply getReplyDetail(Integer authorID, Integer id) {
        return null;
    }
}
