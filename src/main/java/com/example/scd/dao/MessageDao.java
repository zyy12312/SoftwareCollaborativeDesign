package com.example.scd.dao;

import com.example.scd.entity.Message;

import java.util.List;

public interface MessageDao {
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
//    Integer addNewMessage(Integer senderID);
      Integer addNewMessage(Message message);
    /**
     方法名称；getMessageDetail
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Message getMessageDetail(Integer senderID);
    /**
     方法名称；getMessageList
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    List<Message> getMessageList();
    /**
     方法名称；addnewMessageList
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    List<Message> addnewMessageList();

}
