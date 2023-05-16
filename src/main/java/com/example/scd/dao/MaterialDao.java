package com.example.scd.dao;

import com.example.scd.entity.Material;

import java.util.List;

public interface MaterialDao {
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer updateInNewMaterial(Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer addNewMaterial(Material material);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer deleteMaterial(Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer modifyMaterial(Material material);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Material getMaterial(Integer id);
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    List<Material> getMaterialList();
    /**
     方法名称；addNewMessage
     参数：无
     方法功能：发送消息，返回成功的记录数
     返回值：成功的记录数（Integer）
     */
    Integer addMaterialList(List<Material> materialList);
}
