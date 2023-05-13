package com.example.scd.dao.impl;

import com.example.scd.dao.ReplyDao;
import com.example.scd.entity.Reply;

import java.util.List;

public class ReplyDaoImpl implements ReplyDao {
    @Override
    public List<Reply> getReplyList(Integer authorID, Integer id) {
        return null;
    }

    @Override
    public Integer addNewReply(Integer authorID, Integer id) {
        return null;
    }

    @Override
    public Integer deleteReply(Integer authorID, Integer id) {
        return null;
    }

    @Override
    public Integer modifyReply(Integer authorID, Integer id) {
        return null;
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
