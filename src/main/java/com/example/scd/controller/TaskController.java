package com.example.scd.controller;

import com.example.scd.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result getTaskList(){
      return null;
    }
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result getTaskDetail(){return null;}
    @RequestMapping(value = "/createTask",method = RequestMethod.POST)
    public Result createTask(){return null;}
//    @RequestMapping(value = "")
}
