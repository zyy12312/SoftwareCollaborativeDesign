package com.example.scd.service;

import com.example.scd.entity.Material;

import java.util.List;

public interface MaterialService {
    /**
     方法名称；teacherUpdateMeterials
     参数：无
     方法功能：供教师上传资料
     返回值：成功的记录数（Integer）
     */
    Integer teacherUpdateMeterials();
    /**
     方法名称；studentReceiveMeterials
     参数：无
     方法功能：学生获取已发布的资料列表
     返回值：资料列表（List<Material>）
     ？？？
     */
    Integer studentReceiveMeterials();
    /**
     方法名称；getMeterialList
     参数：无
     方法功能：获取所有资料列表
     返回值：资料列表（List<Material>）
     */
    List<Material> getMeterialList();
}
