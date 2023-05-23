package com.example.scd.dao.impl;

import com.example.scd.dao.DiscussDao;
import com.example.scd.entity.Discuss;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiscussDaoImplTest {
    DiscussDao discussDao = new DiscussDaoImpl();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void getDiscussList() {
        List<Discuss> discussList = discussDao.getDiscussList();
        System.out.println(discussList);
        List<Discuss> expectList = new ArrayList<>();
        expectList.add(new Discuss(1,"讨论测试","测试讨论功能",null, LocalDateTime.parse("2023-05-22 10:15:43",dateTimeFormatter),8));
        expectList.add(new Discuss(2,"讨论测试","测试讨论功能",null, LocalDateTime.parse("2023-05-22 10:15:43",dateTimeFormatter),8));
        expectList.add(new Discuss(5,"讨论测试","测试讨论功能",null, LocalDateTime.parse("2023-05-22 00:10:32",dateTimeFormatter),13));
        expectList.add(new Discuss(6, "讨论发布测试1", "测试讨论发布功能", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 10));
        Assert.assertEquals("discuss list error",expectList,discussList);
    }

    @Test
    void addNewDiscuss() {

        Discuss discuss = new Discuss(null, "讨论发布测试", "测试讨论发布功能", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 10);
//        Discuss discuss = new Discuss(null, "讨论删除测试", "测试讨论删除功能", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 10);
        Integer integer = discussDao.addNewDiscuss(discuss);
        Assert.assertEquals("discuss create",1,integer.intValue());
    }

    @Test
    void deleteDiscuss() {
        Integer integer = discussDao.deleteDiscuss(7);
        Assert.assertEquals("discuss delete",1,integer.intValue());
    }

    @Test
    void modifyDiscuss() {
        Discuss discuss = new Discuss(6, "讨论发布测试1", "测试讨论发布功能", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 10);
        Integer integer = discussDao.modifyDiscuss(discuss);
        Assert.assertEquals("discuss edit",1,integer.intValue());
    }

    @Test
    void getDiscussDetail() {
        Discuss discuss = new Discuss(6, "讨论发布测试1", "测试讨论发布功能", null, LocalDateTime.parse("2023-05-22 10:15:43", dateTimeFormatter), 10);
        Discuss discussDetail = discussDao.getDiscussDetail(6);
        System.out.println(discussDetail);
        Assert.assertEquals("discuss detail",discuss,discussDetail);
    }
}
