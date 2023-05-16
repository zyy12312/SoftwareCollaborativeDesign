package com.example.scd.dao.impl;

import com.example.scd.dao.CharacterDao;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.rmi.MarshalledObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CharacterDaoImpl implements CharacterDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public Integer addCharacter(String character) {
        try{
            runner.update("insert into `Character` (`character`) values (?)",character);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public Map<Integer, String> getCharacterMap() {
        Map<Integer,String> characterMap = new HashMap<>();
        try {
            Map<String, Object> map = runner.query("select c.id,c.`character` from `Character` c", new MapHandler());
            for (String id:
            map.keySet()) {
                characterMap.put(Integer.parseInt(id), (String) map.get(id));
            }
            return characterMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getNumOfCharacter() {
        try {
            return runner.query("select count(*) from `Character`",new ScalarHandler<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCharacterByCharacterID(Integer characterID) {
        try {
            return runner.query("select character from `Character` where id = ?",new ScalarHandler<>(),characterID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
