package com.example.scd.controller;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.entity.Result;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.User;
import com.example.scd.service.TaskService;
import com.example.scd.service.TaskService;
import com.example.scd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
@CrossOrigin
public class SubmissionController {
    //    @Autowired
    //    private SubmissionDao submissionDao;
    @Autowired
    private TaskService taskService;

    // 添加任务提交
    @RequestMapping(value = "/addSubmission",method = RequestMethod.POST)
    @ResponseBody
    public Result addSubmission(@RequestBody Submission submission){
        User currentUser = Util.getCurrentUser();
        Integer result = null;
        String message = null;
        if(currentUser.getId() == submission.getSubmitterID()){
            try {
                result = taskService.studentSubmit(submission);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        }
        if (result == null || result == 0) {
            return Result.fail("提交失败！请检查必填字段是否完整");
        }
        return Result.succ(200, "提交成功", null);
    }

    //修改提交内容
    @RequestMapping(value = "/editSubmission",method = RequestMethod.POST)
    @ResponseBody
    public Result editSubmission(@RequestBody Submission submission){
        User currentUser = Util.getCurrentUser();
        Integer result = null;
        String message = null;
        if(currentUser.getId() == submission.getSubmitterID()) {
            try {
                result = taskService.modifySubmission(submission);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        }else{
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        if (result == null || result == 0) {
            return Result.fail(500, "修改失败！");
        }
        return Result.succ(200, "修改成功！", null);
    }

    //获取小组对一级任务的提交
    @RequestMapping(value = "/getSubmissionToTask",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubmissionToTask(@RequestParam Integer taskID){
        User currentUser = Util.getCurrentUser();
        List<Submission> submissionList = null;
        String message = null;
        //教师操作，可获取所有小组的提交
        try{
            if(currentUser.getRole() == 1){
                //taskType为0，代表一级任务
                submissionList = taskService.getAllTeamSubmission(taskID, 0);
            }else if(currentUser.getRole() == 0){
                //学生操作，只能获取本小组的提交情况
                submissionList = taskService.getTeamSubmission(currentUser.getTeamId(), taskID, 0);
            }else{
                return Result.fail(405,"无权限进行此操作！",null);
            }
        }catch (Exception e){
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(submissionList);
    }

    //获取小组成员对二级任务的所有提交
    @RequestMapping(value = "/getSubmissionListToSubTask",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubmissionListToSubTask(@RequestParam Integer subTaskID){
        User currentUser = Util.getCurrentUser();
        List<Submission> submissionList =null;
        String message = null;
        try {
            //taskType为1，代表子任务
            taskService.getTeamSubmission(currentUser.getTeamId(),subTaskID,1);
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(submissionList);
    }
}
