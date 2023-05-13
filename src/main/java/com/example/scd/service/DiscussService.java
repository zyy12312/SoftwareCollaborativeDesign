package com.example.scd.service;

import com.example.scd.entity.Discuss;
import com.example.scd.entity.Reply;

import java.util.List;

public interface DiscussService {
    /**
     方法名称；studentEditDiscuss
     参数：无
     方法功能：供学生编辑自己发布的讨论信息
     返回值：成功的记录数（Integer）
     */
    Integer studentEditDiscuss();
    /**
     方法名称；studentEditReply
     参数：无
     方法功能：供学生编辑自己发布的回复信息
     返回值：成功的记录数（Integer）
     */
    Integer studentEditReply();
    /**
     方法名称；teacherEditDiscuss
     参数：无
     方法功能：供教师编辑自己发布的讨论信息
     返回值：成功的记录数（Integer）
     */
    Integer teacherEditDiscuss();
    /**
     方法名称；teacherEditReply
     参数：无
     方法功能：供教师编辑自己发布的回复信息
     返回值：成功的记录数（Integer）
     */
    Integer teacherEditReply();
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
    List<Reply> showReplyList();
}
