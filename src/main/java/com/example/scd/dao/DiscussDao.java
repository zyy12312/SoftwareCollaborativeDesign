package com.example.scd.dao;

import com.example.scd.entity.Discuss;

import java.util.List;

public interface DiscussDao {
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    List<Discuss> getDiscussList(Integer authorID,Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer addNewDiscuss(Integer authorId,Discuss discuss);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer deleteDiscuss(Integer authorID,Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer modifyDiscuss(Integer authorID,Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer addDiscussList(List<Discuss> discussList);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Discuss getDiscussDetail(Integer authorID,Integer id);

}
