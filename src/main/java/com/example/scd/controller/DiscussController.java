package com.example.scd.controller;

import com.example.scd.entity.Discuss;
import com.example.scd.entity.Reply;
import com.example.scd.entity.Result;
import com.example.scd.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@Controller
@RequestMapping(value = "/discuss")
@CrossOrigin
public class DiscussController {
    @Autowired
    private DiscussService discussService;
    //创建讨论
    @RequestMapping(value = "/createDiscuss",method = RequestMethod.POST)
    @ResponseBody
    public Result createDiscuss(@RequestBody Discuss discuss){

        return null;
    }
    //创建回复
    @RequestMapping(value = "/createReply",method = RequestMethod.POST)
    @ResponseBody
    public Result createReply(@RequestBody Reply reply){

        return null;
    }
    //编辑讨论
    @RequestMapping(value = "/editDiscuss",method = RequestMethod.POST)
    @ResponseBody
    public Result editDiscuss(@RequestBody Discuss discuss){

        return null;
    }
    //编辑回复
    @RequestMapping(value = "/editReply",method = RequestMethod.POST)
    @ResponseBody
    public Result editReply(@RequestBody Reply reply){

        return null;
    }
    //获取讨论列表
    @RequestMapping(value = "/getDiscussList",method = RequestMethod.GET)
    @ResponseBody
    public Result getDiscussList(){

        return null;
    }
    //获取某讨论的回复列表
    @RequestMapping(value = "/getReplyList",method = RequestMethod.GET)
    @ResponseBody
    public Result getReplyList(@RequestParam Integer discussID){

        return null;
    }
    //（可单个可批量）删除讨论（及其下辖所有回复）
    @RequestMapping(value = "/deleteDiscuss",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteDiscuss(@RequestBody List<Integer> discussIDList){

        return null;
    }
    //（可单个可批量）删除回复（及其下辖所有回复）
    @RequestMapping(value = "/deleteReply",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteReply(@RequestBody List<Integer> replyIDList){

        return null;
    }
}
