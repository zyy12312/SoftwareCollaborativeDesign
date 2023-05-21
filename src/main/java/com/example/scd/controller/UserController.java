package com.example.scd.controller;

import com.example.scd.dao.AccountDao;
import com.example.scd.entity.Result;
import com.example.scd.entity.User;
import com.example.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        Integer result = null;
        String message = null;
        try {
            result = userService.addUser(user);
        } catch (Exception e) {
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        if(result == null || result == 2){
            return Result.fail(500,"注册失败！");
        }
        if(result == 1){
            return Result.fail(400,"用户名已存在！",null);
        }else if(result == 0){
            return Result.succ(200,"注册成功！",null);
        }
        return Result.fail(500,"系统出错！");
    }

//    //登录
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @ResponseBody
//    public Result Login(@RequestBody User user){
//        Integer result = null;
//        String message = null;
//        try {
//            result = userService.
//        } catch (Exception e) {
//            String exception = e.getMessage();
//            if(exception.contains("SQLException")){
//                message = "数据库异常！";
//            }else{
//                message = "系统出错！";
//            }
//            return Result.fail(500,message);
//        }
//        if(result == null || result == 0){
//            return Result.fail(500,"注册失败！");
//        }
//        return Result.succ("注册成功！");
//    }

    //修改用户信息
    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    @ResponseBody
    public Result editUser(@RequestBody User user){
        Integer result = null;
        String message = null;
        try {
            result = userService.updateUser(user);
        } catch (Exception e) {
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        if(result == null || result == 0){
            return Result.fail(500,"修改失败！");
        }
        return Result.succ("修改成功！");
    }

    //查询未组队的学生
    @RequestMapping(value = "/getUnGroupedStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Result getUnGroupedStudentList(){
        List<User> allStudent = null;
        String message = null;
        try {
            allStudent = userService.getAllStudent();
        } catch (Exception e) {
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        return Result.succ(allStudent);
    }

    //查询所有学生
    @RequestMapping(value = "/getAllStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllStudentList(){
        List<User> allStudent = null;
        String message = null;
        try {
            allStudent = userService.getAllStudent();
        } catch (Exception e) {
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        return Result.succ(allStudent);
    }
    //查询所有用户
    @RequestMapping(value = "/getAllUserList",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllUserList(){
        List<User> allUser = null;
        String message = null;
        try {
            allUser = userService.getAllUser();
        } catch (Exception e) {
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
      return Result.succ(allUser);
    }
    @RequestMapping(value = "/logout",method = RequestMethod.DELETE)
//    @ResponseBody
    public Result logout(){
        return Result.succ(200);
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User user){
        return null;
    }
}
