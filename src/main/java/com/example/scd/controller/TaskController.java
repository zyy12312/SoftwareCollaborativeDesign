package com.example.scd.controller;

import com.example.scd.entity.*;
import com.example.scd.service.TaskService;
import com.example.scd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.scd.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 未完成接口：
 * 1、获取任务详情
 * 2、获取子任务详情
 * 3、创建子任务
 * 4、删除子任务
 */
@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/taskList", method = RequestMethod.GET)
    @ResponseBody
    public Result getTaskList() {
        List<Task> taskList = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        //若是老师，可以看到已发布和未发布的作业
        try {
            if (currentUser.getRole() == 1) {
                taskList = taskService.getTaskList();
            } else {
                // 若是学生，只能看到已发布的作业
                taskList = taskService.getAllPublishedTasks();
            }
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(taskList);
    }

//    @RequestMapping(value = "/detail", method = RequestMethod.GET)
//    @ResponseBody
//    public Result getTaskDetail(@RequestParam Integer taskID) {
//        return null;
//    }

    @RequestMapping(value = "/subTaskList", method = RequestMethod.GET)
    @ResponseBody
    public Result getSubTaskList(@RequestParam Integer taskID,@RequestParam Integer teamID) {
        List<Subtask> subtaskList = null;
        String message = null;
        Task taskByTaskId = taskService.getTaskByTaskId(taskID);
        if(taskByTaskId == null){
            return Result.fail(406, "一级任务不存在，无法查询到二级任务！");
        }
        User currentUser = Util.getCurrentUser();
        try {
            if(currentUser.getRole() == 0){
                subtaskList = taskService.getSubtasksOfTask(currentUser.getTeamId(), taskID);
            }else if(currentUser.getRole() == 1){
                subtaskList = taskService.getSubtasksOfTask(teamID, taskID);
            }
        } catch (Exception e) {
            String exception = e.getMessage();
            if (exception.contains("SQLException")) {
                message = "数据库异常！";
            } else {
                message = "系统出错！";
            }
            return Result.fail(500, message);
        }
        return Result.succ(subtaskList);
    }

    @RequestMapping(value = "/subTaskDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result getSubTaskDetail(@RequestParam Integer subTaskID) {
        return null;
    }


    @RequestMapping(value = "/createTask", method = RequestMethod.POST)
    @ResponseBody
    public Result createTask(@RequestBody Task task) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = taskService.createTask(task);
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
            return Result.fail("添加失败！请检查字段是否完整！");
        }
        return Result.succ(200, "创建成功", null);
    }

    @RequestMapping(value = "/releaseTask", method = RequestMethod.POST)
    @ResponseBody
    public Result releaseTask(@RequestBody List<Integer> taskIDList) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = taskService.releaseTask(taskIDList);
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
            return Result.fail(406, "部分或全部发布失败！");
        }
        return Result.succ(200, "发布成功！", null);
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteTask(@RequestBody List<Integer> taskIDList) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = taskService.deleteTask(taskIDList);
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
            return Result.fail(406, "部分或全部删除失败！");
        }
        return Result.succ(200, "删除成功！", null);
    }

    @RequestMapping(value = "/editTask", method = RequestMethod.POST)
    @ResponseBody
    public Result editTask(@RequestBody Task task) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        if (currentUser.getRole() == 1) {
            try {
                result = taskService.modifyTask(task);
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
            return Result.fail(406, "修改失败！");
        }
        return Result.succ(200, "修改成功！", null);
    }

    @RequestMapping(value = "/createSubTask", method = RequestMethod.POST)
    @ResponseBody
    public Result createSubTask(@RequestBody List<Subtask> subtask) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        Integer targetID = subtask.get(0).getTargetID();
        Task taskByTaskId = taskService.getTaskByTaskId(targetID);
        if(taskByTaskId != null){
            if (currentUser.getTeam().getStudentCharacter() == taskByTaskId.getCharacterType()) {
                try {
                    result = taskService.createSubTask(subtask);
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
        }
        if (result == null || result == 0) {
            return Result.fail(406,"添加失败！",null);
        }
        return Result.succ(200, "创建成功", null);
    }


    @RequestMapping(value = "/deleteSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSubTask(@RequestBody List<Integer> subtaskIDList){
        //缺异常捕获和权限判断
        for (Integer taskId:
                subtaskIDList) {
            taskService.deleteSubtask(taskId);
        }
        return null;
    }

    @RequestMapping(value = "/editSubTask", method = RequestMethod.POST)
    @ResponseBody
    public Result editSubTask(@RequestBody Subtask subtask) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        Task task = taskService.getTaskByTaskId(subtask.getTargetID());
        if (currentUser.getTeam().getStudentCharacter() == task.getCharacterType()) {
            try {
                result = taskService.modifySubtask(subtask);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
            if (result == null || result == 0) {
                return Result.fail(406, "修改失败！");
            }
        } else {
            message = "无权限进行此操作！";
            return Result.fail(405, message);
        }
        return Result.succ(200, "修改成功！", null);
    }
}
