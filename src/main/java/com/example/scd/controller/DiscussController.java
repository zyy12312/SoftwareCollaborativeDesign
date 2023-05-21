package com.example.scd.controller;

import com.example.scd.entity.*;
import com.example.scd.service.DiscussService;
import com.example.scd.utils.GsonGenerator;
import com.example.scd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * //获取某讨论的回复列表
 * //删除帖子--无判断权限
 * //删除回复--无判断权限
 */
@Controller
@RequestMapping(value = "/discuss")
@CrossOrigin
public class DiscussController {
    @Autowired
    private DiscussService discussService;

    //创建讨论
    @RequestMapping(value = "/createDiscuss", method = RequestMethod.POST)
    @ResponseBody
    public Result createDiscuss(@RequestBody Discuss discuss) {
//        System.out.println(s);
//        Discuss discuss = GsonGenerator.gsonSetter().fromJson(s, Discuss.class);
        System.out.println(discuss);
        Integer result = null;
        String resultMessage = null;
        try {
            result = discussService.addDiscuss(discuss);
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
            return Result.fail("发布创建失败！");
        }
        return Result.succ(200, "发布讨论成功！", null);
    }

    //创建回复
    @RequestMapping(value = "/createReply", method = RequestMethod.POST)
    @ResponseBody
    public Result createReply(@RequestBody Reply reply) {
        Integer result = null;
        String resultMessage = null;
        try {
            result = discussService.addReply(reply);
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
            return Result.fail("回复失败！");
        }
        return Result.succ(200, "回复成功！", null);
    }

    //编辑讨论
    @RequestMapping(value = "/editDiscuss", method = RequestMethod.POST)
    @ResponseBody
    public Result editDiscuss(@RequestBody Discuss discuss) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getId() == discuss.getAuthorID()) {
            try {
                result = discussService.editDiscuss(discuss);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail(500, "修改失败！");
        }
        return Result.succ(200, "修改成功！", null);
    }

    //编辑回复
    @RequestMapping(value = "/editReply", method = RequestMethod.POST)
    @ResponseBody
    public Result editReply(@RequestBody Reply reply) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getId() == reply.getAuthorID()) {
            try {
                result = discussService.editReply(reply);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail(500, "修改失败！");
        }
        return Result.succ(200, "修改成功！", null);
    }

    //获取讨论列表
    @RequestMapping(value = "/getDiscussList", method = RequestMethod.GET)
    @ResponseBody
    public Result getDiscussList() {
        List<Discuss> discussList = null;
        String message = null;
        //若是老师，可以看到已发布和未发布的作业
        try {
            discussList = discussService.showDiscussList();
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(discussList);
    }

    //获取某讨论的回复列表
    @RequestMapping(value = "/getReplyList", method = RequestMethod.GET)
    @ResponseBody
    public Result getReplyList(@RequestParam Integer discussID) {
        List<Reply> replyList = null;
        String message = null;
        //若是老师，可以看到已发布和未发布的作业
        try {
            replyList = discussService.showReplyList(discussID);
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(replyList);
    }

    //（可单个可批量）删除讨论（及其下辖所有回复）
    @RequestMapping(value = "/deleteDiscuss", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteDiscuss(@RequestBody List<Integer> discussIDList) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
//        if (currentUser.getId() == reply.getAuthorID()) {
        try {
            result = discussService.deleteDiscuss(discussIDList);
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
//        else{
//            message = "无权限进行此操作！";
//            return Result.fail(405, message);
//        }
        if (result == null || result == 0) {
            return Result.fail(500, "部分或全部删除失败！",null);
        }
        return Result.succ(200,"删除成功！",null);
    }

    //（可单个可批量）删除回复（及其下辖所有回复）
    @RequestMapping(value = "/deleteReply", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteReply(@RequestBody List<Integer> replyIDList) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
//        if (currentUser.getId() == reply.getAuthorID()) {
        try {
            result = discussService.deleteReply(replyIDList);
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
//        else{
//            message = "无权限进行此操作！";
//            return Result.fail(405, message);
//        }
        if (result == null || result == 0) {
            return Result.fail(500, "部分或全部删除失败！",null);
        }
        return Result.succ(200,"删除成功！",null);
    }
}
