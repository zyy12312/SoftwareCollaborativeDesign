package com.example.scd.controller;


import com.example.scd.dao.CharacterDao;
import com.example.scd.entity.Message;
import com.example.scd.entity.Result;
import com.example.scd.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/character")
@CrossOrigin
public class CharacterController {
    @Autowired
    private CharacterDao characterDao;

    //获取消息列表（方式待定）
    @RequestMapping(value = "/getCharacterMap", method = RequestMethod.GET)
    @ResponseBody
    public Result getCharacterMap() {

        Map<Integer, String> characterMap;
        String resultMessage = null;
        try {
            characterMap = characterDao.getCharacterMap();
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                resultMessage = "数据库异常！";
            } else {
                resultMessage = "系统出错！";
            }
            return Result.fail(500, resultMessage);
        }
        return Result.succ(characterMap);
    }
}
