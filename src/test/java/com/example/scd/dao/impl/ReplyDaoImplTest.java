package com.example.scd.dao.impl;

import com.example.scd.dao.ReplyDao;
import com.example.scd.entity.Reply;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReplyDaoImplTest {
    ReplyDao replyDao = new ReplyDaoImpl();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void getReplyList() {
        List<Reply> replyList = replyDao.getReplyList(1);
        System.out.println(replyList);
    }

    @Test
    void deleteReplyByDiscussID() {
        Integer integer = replyDao.deleteReplyByDiscussID(1);
        Assert.assertEquals("delete reply",1,integer.intValue());
    }

    @Test
    void deleteReplyByReplyID() {
    }

    @Test
    void addNewReply() {
        Reply reply = new Reply(null, "reply for delete", null, 13, 1, 0, LocalDateTime.parse("2023-05-22 11:51:32", dateTimeFormatter), null);
        Integer integer = replyDao.addNewReply(reply);
        Assert.assertEquals("add reply",1,integer.intValue());
    }

    @Test
    void deleteReply() {
        Integer integer = replyDao.deleteReply(13, 6);
        Assert.assertEquals("delete reply",1,integer.intValue());

    }

    @Test
    void modifyReply() {
        Reply reply = new Reply(4, "reply for delete", null, 13, 1, 0, LocalDateTime.parse("2023-05-22 11:51:32", dateTimeFormatter), null);
        Integer integer = replyDao.modifyReply(reply);
        Assert.assertEquals("edit reply",1,integer.intValue());
    }

    @Test
    void addNewReplyList() {
    }

    @Test
    void getReplyDetail() {

    }
}
