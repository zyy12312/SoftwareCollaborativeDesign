package com.example.scd.dao.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CharacterDaoImplTest {
    CharacterDaoImpl characterDao = new CharacterDaoImpl();
    @Test
    void addCharacter() {
        Integer integer = characterDao.addCharacter("测试角色1");
        Assert.assertEquals("error",1,integer.intValue());
    }

    @Test
    void getCharacterMap() {
        Map<Integer, String> characterMap = characterDao.getCharacterMap();
        Map<Integer,String> targetMap = new HashMap<>();
        targetMap.put(1,"组长");
        targetMap.put(2,"产品经理");
        targetMap.put(3,"开发经理");
        targetMap.put(4,"计划质量经理");
        targetMap.put(5,"测试经理");
        targetMap.put(13,"测试角色1");
        Assert.assertEquals("error",targetMap,characterMap);
    }

    @Test
    void getNumOfCharacter() {
        Long numOfCharacter = characterDao.getNumOfCharacter();
        Assert.assertEquals("error",6,numOfCharacter.longValue());
    }

    @Test
    void getCharacterByCharacterID() {
        String characterByCharacterID = characterDao.getCharacterByCharacterID(1);
        Assert.assertEquals("error","组长",characterByCharacterID);
    }
}
