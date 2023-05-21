package com.example.scd.service;

import com.example.scd.entity.Discuss;
import com.example.scd.entity.Reply;

import java.util.List;

public interface DiscussService {
    Integer addDiscuss(Discuss discuss);

    /**
     方法名称；editDiscuss
     参数：Discuss
     方法功能：供教师编辑自己发布的讨论信息
     返回值：成功的记录数（Integer）
     */
    Integer addReply(Reply reply);
    Integer editDiscuss(Discuss discuss);
    /**
     方法名称；editReply
     参数：Reply
     方法功能：供教师编辑自己发布的回复信息
     返回值：成功的记录数（Integer）
     */
    Integer editReply(Reply reply);
    /**
     方法名称；deleteDiscuss
     参数：discussID
     方法功能：供用户删除自己发布的讨论信息
     返回值：成功的记录数（Integer）
     */
    Integer deleteDiscuss(List<Integer> discussIDList);
    /**
     方法名称；deleteReply
     参数：replyID
     方法功能：供用户删除自己发布的回复信息
     返回值：成功的记录数（Integer）
     */
    Integer deleteReply(List<Integer> replyIDList);
    /**
     方法名称；showDiscussList
     参数：无
     方法功能：获取讨论列表
     返回值：讨论列表（List<Discuss>）
     */
    List<Discuss> showDiscussList();
    /**
     方法名称；showReplyList
     参数：无
     方法功能：获取讨论的回复列表
     返回值：回复列表（List<Reply>）
     */
    List<Reply> showReplyList(Integer discussID);
}
