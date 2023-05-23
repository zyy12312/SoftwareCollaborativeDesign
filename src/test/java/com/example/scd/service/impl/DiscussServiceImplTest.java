package com.example.scd.service.impl;

import com.example.scd.dao.DiscussDao;
import com.example.scd.entity.Discuss;
import com.example.scd.entity.Reply;
import com.example.scd.service.DiscussService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class DiscussServiceImplTest {
    @Autowired
    DiscussService discussService;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void addDiscuss() {
        Discuss discuss = new Discuss(1, "讨论测试", "测试讨论功能", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 8);
        Integer integer = discussService.addDiscuss(discuss);
        Assert.assertEquals("add discuss",1,integer.intValue());
    }

    @Test
    void addReply() {
        Reply reply = new Reply(null, "reply for delete", null, 13, 1, 0, LocalDateTime.parse("2023-05-22 11:51:32", dateTimeFormatter), null);
        Integer integer = discussService.addReply(reply);
        Assert.assertEquals("add reply",1,integer.intValue());
    }

    @Test
    void editDiscuss() {
        Discuss discuss = new Discuss(1, "讨论测试", "测试讨论功能1", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 8);
        Integer integer = discussService.editDiscuss(discuss);
        Assert.assertEquals("edit discuss",1,integer.intValue());
    }

    @Test
    void editReply() {
        Reply reply = new Reply(8, "reply for delete1", null, 13, 1, 0, LocalDateTime.parse("2023-05-22 11:51:32", dateTimeFormatter), null);
        Integer integer = discussService.editReply(reply);
        Assert.assertEquals("edit reply",1,integer.intValue());
    }

    @Test
    void deleteDiscuss() {
        List<Integer> integers = new ArrayList<>();
        integers.add(6);
        Integer integer = discussService.deleteDiscuss(integers);
        Assert.assertEquals("delete discuss",1,integer.intValue());
    }

    @Test
    void deleteReply() {
        List<Integer> integers = new ArrayList<>();
        integers.add(8);
        Integer integer = discussService.deleteReply(integers);
        Assert.assertEquals("delete discuss",1,integer.intValue());
    }

    @Test
    void showDiscussList() {
        System.out.println(discussService.showDiscussList());
    }

    @Test
    void showReplyList() {
        System.out.println(discussService.showReplyList(1).size());
    }
}