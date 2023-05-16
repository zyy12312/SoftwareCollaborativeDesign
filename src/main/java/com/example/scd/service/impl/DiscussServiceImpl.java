package com.example.scd.service.impl;

import com.example.scd.dao.DiscussDao;
import com.example.scd.dao.ReplyDao;
import com.example.scd.entity.Discuss;
import com.example.scd.entity.Reply;
import com.example.scd.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author lain,aya
 */
@Service

public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private ReplyDao replyDao;


    @Override
    public Integer editDiscuss(Discuss discuss) {
        if (discuss!=null){
            return discussDao.modifyDiscuss(discuss);
        }
        return 0;
    }

    @Override
    public Integer editReply(Reply reply) {
        if (reply!=null){
            return replyDao.modifyReply(reply);
        }
        return 0;
    }

    @Override
    public Integer deleteDiscuss(Integer discussID) {
        if (discussID!=null){
            replyDao.deleteReplyByDiscussID(discussID);
            return discussDao.deleteDiscuss(discussID);
        }
       return 0;
    }

    @Override
    public Integer deleteReply(Integer replyID) {
        if (replyID!=null){
            return replyDao.deleteReplyByReplyID(replyID);
        }
        return 0;
    }

    @Override
    public List<Discuss> showDiscussList() {
        return discussDao.getDiscussList();
    }

    @Override
    public List<Reply> showReplyList(Integer discussID) {
        if (discussID!=null){
            return replyDao.getReplyList(discussID);
        }
        return null;
    }
}
