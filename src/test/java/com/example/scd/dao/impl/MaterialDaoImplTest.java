package com.example.scd.dao.impl;

import com.example.scd.dao.MaterialDao;
import com.example.scd.entity.Material;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialDaoImplTest {
    MaterialDao materialDao = new MaterialDaoImpl();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void updateInNewMaterial() {

    }

    @Test
    void addNewMaterial() {
//        Material material = new Material(null,"测试资料发布1","此记录用于测试资料发布功能12313",null,0,null, null,LocalDateTime.now());
//        Material material = new Material(null,"测试资料修改1","此记录用于测试资料修改功能12313",null,0,null, null,LocalDateTime.now());
        Material material = new Material(null,"测试资料删除1","此记录用于测试资料删除功能12313",null,0,null, null,LocalDateTime.now());

        Integer integer = materialDao.addNewMaterial(material);
        int i = integer.intValue();
        Assert.assertEquals("error",1,i);
    }

    @Test
    void deleteMaterial() {
        Integer integer = materialDao.deleteMaterial(3);
        int i = integer.intValue();
        Assert.assertEquals("error",1,i);
    }

    @Test
    void modifyMaterial() {

    }

    @Test
    void updateMaterialState() {
        Integer integer = materialDao.updateMaterialState(2);
        int i = integer.intValue();
        System.out.println(i);
        Assert.assertEquals("failed", 1,i);
    }

    @Test
    void getMaterial() {
        Material material = new Material(4,"测试资料发布1","此记录用于测试资料发布功能12313",null,0,null, null,LocalDateTime.parse("2023-05-23 09:01:34",dateTimeFormatter));
        Material material1 = materialDao.getMaterial(4);
        Assert.assertEquals("error",material,material1);
    }

    @Test
    void getMaterialList() {
        List<Material> materialList = new ArrayList<>();
        Material material1 = new Material(4,"测试资料发布1","此记录用于测试资料发布功能12313",null,0,null, null,LocalDateTime.parse("2023-05-23 09:01:34",dateTimeFormatter));
        Material material2 = new Material(5,"测试资料修改1","此记录用于测试资料修改功能12313",null,0,null, null,LocalDateTime.parse("2023-05-23 09:01:41",dateTimeFormatter));
        Material material3 = new Material(6,"测试资料删除1","此记录用于测试资料删除功能12313",null,0,null, null,LocalDateTime.parse("2023-05-23 09:01:46",dateTimeFormatter));
        materialList.add(material1);
        materialList.add(material2);
        materialList.add(material3);
        List<Material> materialList1 = materialDao.getMaterialList();
        Assert.assertEquals("erroe",materialList,materialList1);
    }

    @Test
    void addMaterialList() {

    }
}
