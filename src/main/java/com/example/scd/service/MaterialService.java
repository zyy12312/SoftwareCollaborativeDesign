package com.example.scd.service;

import com.example.scd.entity.Material;

import java.util.List;

public interface MaterialService {
    /**
     方法名称；teacherUpdateMeterial
     参数：无
     方法功能：供教师上传资料
     返回值：成功的记录数（Integer）
     */
    Integer teacherUpdateMaterial(Material material);
    /**
     方法名称；teacherDeleteMeterial
     参数：无
     方法功能：供教师上传资料
     返回值：成功的记录数（Integer）
     */
    Integer teacherDeleteMaterial(Integer materialID);
    /**
     方法名称；teacherEditMeterial
     参数：无
     方法功能：供教师上传资料
     返回值：成功的记录数（Integer）
     */
    Integer teacherEditMaterial(Material material);
    /**
     * 方法名称；studentReceiveMeterials
     * 参数：无
     * 方法功能：学生获取已发布的资料列表
     * 返回值：资料列表（List<Material>）
     */
    List<Material> studentReceiveMaterials();
    /**
     方法名称；getAllMeterials
     参数：无
     方法功能：获取所有资料列表
     返回值：资料列表（List<Material>）
     */
    List<Material> getAllMaterials();
    /**
     方法名称；getAllReleasedMeterials
     参数：无
     方法功能：获取所有已发布的资料列表
     返回值：资料列表（List<Material>）
     */
    List<Material> getAllReleasedMaterials();

}
