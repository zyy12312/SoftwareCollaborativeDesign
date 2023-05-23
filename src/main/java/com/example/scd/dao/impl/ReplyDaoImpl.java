package com.example.scd.dao.impl;

import com.example.scd.dao.ReplyDao;
import com.example.scd.entity.Message;
import com.example.scd.entity.Reply;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 读取回复列表未完成
 */
@Repository
public class ReplyDaoImpl implements ReplyDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public List<Reply> getReplyList(Integer discussID) {
        try {
            return runner.query("select * from Reply where replyTarget = ? and replyIsDiscuss = 0",new BeanListHandler<Reply>(Reply.class),discussID);
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
                    "values (?,?,?,?,?,?)",reply.getDetail(),reply.getFileURL(),reply.getAuthorID(),reply.getReplyTarget(),
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
    public Integer deleteReply(Integer authorID, Integer id) {
        Integer res = null;
        try{
            res = runner.update("delete from Reply where id = ? and authorID = ?",id,authorID);
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
                            "where id = ?",reply.getDetail(),reply.getFileURL(),reply.getAuthorID(),reply.getReplyTarget(),
                    reply.getReplyIsDiscuss(),reply.getReplyTime(),reply.getRid());
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
