package com.example.scd.dao.impl;

import com.example.scd.dao.ReplyDao;
import com.example.scd.entity.Message;
import com.example.scd.entity.Reply;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 读取回复列表未完成
 */
public class ReplyDaoImpl implements ReplyDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public List<Reply> getReplyList(Integer authorID, Integer id) {
        return null;
    }

    @Override
    public Integer addNewReply(Reply reply) {
        try{
            return runner.update("insert into Reply(detail, filesURL, authorID, replyTarget, replyIsDiscuss, replyTime) " +
                    "values (?,?,?,?,?,?)",reply.getDetail(),reply.getFileURL(),reply.getAuthorID(),reply.getReplyTarget(),
                    reply.getReplysDiscuss(),reply.getReplyTime());
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

//    @Override
//    public Integer addNewReply(Integer authorID, Integer id) {
//        return null;
//    }

    @Override
    public Integer deleteReply(Integer authorID, Integer id) {
        try{
            runner.update("delete from Reply where id = ?",id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Integer modifyReply(Reply reply) {
        try{
            return runner.update("update Reply set detail = ?,filesURL = ?,authorID = ?,replyTarget = ?,replyIsDiscuss = ?,replyTime = ?" +
                            "where id = ?",reply.getDetail(),reply.getFileURL(),reply.getAuthorID(),reply.getReplyTarget(),
                    reply.getReplysDiscuss(),reply.getReplyTime(),reply.getRid());
        }catch (SQLException e){
            throw new RuntimeException();
        }
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
