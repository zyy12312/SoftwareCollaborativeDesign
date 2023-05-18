package com.example.scd.controller;

import com.example.scd.dao.SubmissionDao;
import com.example.scd.entity.Result;
import com.example.scd.entity.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submission")
@CrossOrigin
public class SubmissionController {
    @Autowired
    private SubmissionDao submissionDao;
    // 添加任务提交
    @RequestMapping(value = "/addSubmission",method = RequestMethod.POST)
    @ResponseBody
    public Result addSubmission(@RequestBody Submission submission){
        return null;
    }
    //修改提交内容
    @RequestMapping(value = "/editSubmission",method = RequestMethod.POST)
    @ResponseBody
    public Result editSubmission(@RequestBody Submission submission){
        return null;
    }
    //获取小组对一级任务的提交
    @RequestMapping(value = "/getSubmissionToTask",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubmissionToTask(@RequestParam Integer taskID){
        return null;
    }
    //获取小组成员对二级任务的所有提交
    @RequestMapping(value = "/getSubmissionListToSubTask",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubmissionListToSubTask(@RequestParam Integer subTaskID){
        return null;
    }
}
