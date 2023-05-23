package com.example.scd.service.impl;

import com.example.scd.service.DiscussService;
import com.example.scd.service.MaterialService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MaterialServiceImplTest {
    @Autowired
    MaterialService materialService;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Test
    void teacherUpdateMaterial() {

    }

    @Test
    void teacherReleaseMaterial() {
    }

    @Test
    void teacherDeleteMaterial() {
    }

    @Test
    void teacherEditMaterial() {
    }

    @Test
    void studentReceiveMaterials() {
    }

    @Test
    void getAllMaterials() {
    }

    @Test
    void getAllReleasedMaterials() {
    }


}