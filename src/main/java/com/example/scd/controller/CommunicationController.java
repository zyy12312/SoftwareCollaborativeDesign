package com.example.scd.controller;

import com.example.scd.entity.Message;
import com.example.scd.entity.Result;
import com.example.scd.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  //获取消息列表（方式待定）
 */
@Controller
@RequestMapping(value = "/communication")
@CrossOrigin
public class CommunicationController {
    @Autowired
    private CommunicationService communicationService;

    //发送消息
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public Result sendMessage(@RequestBody Message message) {
        Integer result = null;
        String resultMessage = null;
        try {
            result = communicationService.senderSendMessage(message);
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                resultMessage = "数据库异常！";
            } else {
                resultMessage = "系统出错！";
            }
            return Result.fail(500, resultMessage);
        }
        if (result == null || result == 0) {
            return Result.fail("发送失败！");
        }
        return Result.succ(200, "发送成功", null);
    }

    //获取消息列表（方式待定）
    @RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
    @ResponseBody
    public Result getMessageList(@RequestParam Integer teamID) {
        List<Message> messageList = null;
        String resultMessage = null;
        try {
            messageList = communicationService.getSendMessageList(teamID);
        } catch (Exception e) {
            String exception = e.getMessage();
            System.out.println(exception);
            if (exception.contains("SQLException")) {
                resultMessage = "数据库异常！";
            } else {
                resultMessage = "系统出错！";
            }
            return Result.fail(500, resultMessage);
        }
        return Result.succ(200, "获取消息成功！", messageList);
    }

}
