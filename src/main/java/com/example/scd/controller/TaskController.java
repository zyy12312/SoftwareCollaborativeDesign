package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    @RequestMapping(value = "/taskList",method = RequestMethod.GET)
    @ResponseBody
    public Result getTaskList(){
      return null;
    }
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Result getTaskDetail(@RequestParam Integer taskID){return null;}
    @RequestMapping(value = "/subTaskList",method = RequestMethod.GET)
    @ResponseBody
    public Result getSubTaskList(@RequestParam Integer taskID){
        return null;
    }
    @RequestMapping(value = "/subTaskDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result getSubTaskDetail(@RequestParam Integer subTaskID){return null;}
    @RequestMapping(value = "/createTask",method = RequestMethod.POST)
    @ResponseBody
    public Result createTask(@RequestBody Task task){return null;}
    @RequestMapping(value = "/createSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result createSubTask(@RequestBody List<Subtask> subtask){return null;}
    @RequestMapping(value = "/editTask",method = RequestMethod.POST)
    @ResponseBody
    public Result editTask(@RequestBody Task task){return null;}
    @RequestMapping(value = "/editSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result editSubTask(@RequestBody Subtask subtask){return null;}
    @RequestMapping(value = "/releaseTask",method = RequestMethod.GET)
    @ResponseBody
    public Result releaseTask(@RequestParam List<Integer> taskIDList){return null;}
    @RequestMapping(value = "/deleteTask",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteTask(@RequestBody List<Integer> taskIDList){return null;}
    @RequestMapping(value = "/deleteSubTask",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSubTask(@RequestBody List<Integer> subtaskIDList){return null;}


//    @RequestMapping(value = "")
}
