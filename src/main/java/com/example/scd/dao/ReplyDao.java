package com.example.scd.dao;

import com.example.scd.entity.Reply;

import java.util.List;

public interface ReplyDao {
    /**
     方法名称；getReplyList
     参数：无
     方法功能：获取回复列表
     返回值：回复列表
     */
    List<Reply> getReplyList(Integer id);
//    /**
//     方法名称；getReplyList
//     参数：无
//     方法功能：获取回复列表
//     返回值：回复列表
//     */
//    List<Reply> getReplytoReplyList(Integer id);
    /**
     方法名称；deleteReplyByDiscussID
     参数：discussId
     方法功能：删除某条discuss的所有评论
     返回值：删除评论总数
     */
    Integer deleteReplyByDiscussID(Integer discussId);
    /**
     方法名称；deleteReplyByReplyID
     参数：replyId
     方法功能：删除某条reply的所有跟评
     返回值：删除评论总数
     */
    Integer deleteReplyByReplyID(Integer replyId);
    /**
     方法名称；addNewReply
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
//    Integer addNewReply(Integer authorID,Integer id);
    Integer addNewReply(Reply reply);
    /**
     方法名称；deleteReply
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer deleteReply(Integer id);
    /**
     方法名称；modifyReply
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
//    Integer modifyReply(Integer authorID,Integer id);
    Integer modifyReply(Reply reply);
    /**
     方法名称；addNewReplyList
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer addNewReplyList(List<Reply> replyList);
    /**
     方法名称；getReplyDetail
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Reply getReplyDetail(Integer authorID,Integer id);
}
