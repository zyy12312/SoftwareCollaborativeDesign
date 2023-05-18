package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.entity.Submission;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import com.example.scd.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库查询中抛出的异常是否要用该方法统一处理：
 * https://blog.csdn.net/Leon_Jinhai_Sun/article/details/125342002
 */

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/taskList",method = RequestMethod.GET)
    @ResponseBody
    public Result getTaskList(){
        //若是老师，可以看到已发布和未发布的作业
        // 若是学生，只能看到已发布的作业
        List<Task> taskList = null;
        if(true){  //若当前用户是老师
            taskList = taskService.getTaskList();
        }else{  //当前用户是学生
            taskList = taskService.getAllPublishedTasks();
        }
        if(taskList == null){

        }
        return Result.succ(taskList);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Result getTaskDetail(@RequestParam Integer taskID){
        return null;
    }

    @RequestMapping(value = "/subTaskList",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubTaskList(@RequestParam Integer taskID){
        return null;
    }

    @RequestMapping(value = "/subTaskDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result getSubTaskDetail(@RequestParam Integer subTaskID){
        Map<String,Object> mapData = new HashMap<String,Object>();
        Subtask subtask = taskService.getSubtask(subTaskID);
        if(subtask == null){
            return Result.fail("未查询到对应信息！");
        }
        //获取当前用户信息，假设获取小组号是1
        //获取当前任务id，从前端获取还是后端查询
        //若后端查询
        Integer targetID = subtask.getTargetID();
        //taskType为1，子任务
        List<Submission> submissionList = taskService.getTeamSubmission(1, targetID, 1);
        mapData.put("subtask",subtask);
        mapData.put("submissionList",submissionList);
        return Result.succ(mapData);
    }


    @RequestMapping(value = "/createTask",method = RequestMethod.POST)
    @ResponseBody
    public Result createTask(@RequestBody Task task){
        //手动处理判断task必须字段是否为空，还是捕获数据库抛出的异常然后返回fail
        final Integer result = taskService.createTask(task);
        //好像不用判断result是否为0还1
        //是否要判断这种情况的出现：同一个小组中出现多个学生担任一样的角色
        return Result.succ(null);
    }

    @RequestMapping(value = "/createSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result createSubTask(@RequestBody List<Subtask> subtask){
        //List,一次创建多个子任务吗
        return null;
    }

    @RequestMapping(value = "/editTask",method = RequestMethod.POST)
    @ResponseBody
    public Result editTask(@RequestBody Task task){
        Integer result = taskService.modifyTask(task);
        //同样，待添加捕获异常代码
        return null;
    }


    @RequestMapping(value = "/editSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result editSubTask(@RequestBody Subtask subtask){
        Integer result = taskService.modifyTask(subtask);
        return null;
    }

    @RequestMapping(value = "/releaseTask",method = RequestMethod.GET)
    @ResponseBody
    public Result releaseTask(@RequestParam List<Integer> taskIDList){
        //缺异常捕获
        for (Integer taskId:
             taskIDList) {
            taskService.releaseTask(taskId);
        }
        return null;
    }

    @RequestMapping(value = "/deleteTask",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteTask(@RequestBody List<Integer> taskIDList){
        //缺异常捕获
        for (Integer taskId:
                taskIDList) {
            taskService.deleteTask(taskId);
        }
        return null;
    }

    @RequestMapping(value = "/deleteSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSubTask(@RequestBody List<Integer> subtaskIDList){
        //缺异常捕获
        for (Integer taskId:
                subtaskIDList) {
            taskService.deleteSubtask(taskId);
        }
        return null;
    }


//    @RequestMapping(value = "")
}
