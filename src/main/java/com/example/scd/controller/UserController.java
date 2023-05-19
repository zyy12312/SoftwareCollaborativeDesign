package com.example.scd.controller;

import com.example.scd.dao.AccountDao;
import com.example.scd.entity.Result;
import com.example.scd.entity.User;
import com.example.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    //创建用户
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    @ResponseBody
    public Result createUser(@RequestBody User user){
        userService.addUser(user);
        return null;
    }


    //修改用户信息
    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    @ResponseBody
    public Result editUser(@RequestBody User user){
        return null;
    }


    //查询未组队的学生
    @RequestMapping(value = "/getUnGroupedStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Result getUnGroupedStudentList(){
        return null;
    }
    //查询所有学生
    @RequestMapping(value = "/getAllStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllStudentList(){
        return null;
    }
    //查询所有用户
    @RequestMapping(value = "/getAllUserList",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllUserList(){
        return null;
    }
    @RequestMapping(value = "/logout",method = RequestMethod.DELETE)
    @ResponseBody
    public Result logout(){
        return null;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User user){
        return null;
    }
}
