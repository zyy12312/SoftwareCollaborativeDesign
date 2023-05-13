package com.example.scd.service;

import com.example.scd.entity.Message;

import java.util.List;

public interface CommunicationService {
    /**
     方法名称；senderSendMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer senderSendMessage();
    /**
     方法名称；getSendMessageList
     参数：无
     方法功能：获取消息列表
     返回值：消息列表（List<Message>）
     */
    List<Message> getSendMessageList();
    /**
     方法名称；getMessageDetail
     参数：无
     方法功能：获取消息详情
     返回值：消息详情（Message）
     */
    Message getMessageDetail();
}
