package com.example.scd.controller;

import com.example.scd.entity.Message;
import com.example.scd.entity.Result;
import com.example.scd.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/communication")
@CrossOrigin
public class CommunicationController {
    @Autowired
    private CommunicationService communicationService;
    //发送消息
    @RequestMapping("/sendMessage")
    @ResponseBody
    public Result sendMessage(@RequestBody Message message){

        return null;
    }
    //获取消息列表（方式待定）
    @RequestMapping("/getMessageList")
    @ResponseBody
    public Result getMessageList(@RequestBody Message message){

        return null;
    }

}
