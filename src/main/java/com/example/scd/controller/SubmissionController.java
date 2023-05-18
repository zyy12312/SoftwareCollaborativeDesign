package com.example.scd.controller;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.entity.Result;
import com.example.scd.entity.Submission;
import com.example.scd.service.TaskService;
import com.example.scd.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        taskService.studentSubmit(submission);
        return null;
    }

    //修改提交内容
    @RequestMapping(value = "/editSubmission",method = RequestMethod.POST)
    @ResponseBody
    public Result editSubmission(@RequestBody Submission submission){
        taskService.updateSubmit(submission);
        return null;
    }

    //获取小组对一级任务的提交
    @RequestMapping(value = "/getSubmissionToTask",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubmissionToTask(@RequestBody Integer taskID){
        //判断当前用户是教师还是学生
        //如果是教师，获取一级任务所有小组的提交
//        if(true){
//            taskService.getAllTeamSubmission(taskID,0);
//        }else{
//            //如果是学生，获取其小组的提交
//            taskService.get
//        }
//        return null;
        return null;
    }

    //获取小组成员对二级任务的所有提交
    @RequestMapping(value = "/getSubmissionListToSubTask",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubmissionListToSubTask(@RequestParam Integer subTaskID){
        return null;
    }
}
