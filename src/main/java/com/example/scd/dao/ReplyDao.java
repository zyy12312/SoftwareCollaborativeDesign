package com.example.scd.dao;

import com.example.scd.entity.Reply;

import java.util.List;

public interface ReplyDao {
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    List<Reply> getReplyList(Integer authorID,Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
//    Integer addNewReply(Integer authorID,Integer id);
    Integer addNewReply(Reply reply);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer deleteReply(Integer authorID,Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
//    Integer modifyReply(Integer authorID,Integer id);
    Integer modifyReply(Reply reply);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer addNewReplyList(List<Reply> replyList);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Reply getReplyDetail(Integer authorID,Integer id);
}
