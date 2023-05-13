package com.example.scd.dao;

import com.example.scd.entity.Character;

import java.util.List;
import java.util.Map;

public interface CharacterDao {
    //添加角色标签
    Integer addCharacter(String character);
    //获取所有角色标签
    Map<Integer,String> getCharacterMap();
    //获取角色数量
    Long getNumOfCharacter();
}
