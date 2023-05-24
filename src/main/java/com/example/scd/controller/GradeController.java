package com.example.scd.controller;

import com.example.scd.entity.Result;
import com.example.scd.entity.Task;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.GradeService;
import com.example.scd.service.UserService;
import com.example.scd.utils.GsonGenerator;
import com.example.scd.utils.Util;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/grade")
@CrossOrigin
public class GradeController {
    @Autowired
    GradeService gradeService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/inputGrade", method = RequestMethod.POST)
    @ResponseBody
    public Result inputGrade(@RequestBody Map<String, Object> map) {
        Integer result = null;
        String message = null;
        User currentUser = Util.getCurrentUser();
        Double finalScore = (Double) map.get("finalScore");
        Integer studentID = (Integer) map.get("studentID");
        if (currentUser.getRole() == 1) {
            try {
                result = userService.updateStudentFinalScore(studentID,finalScore);
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
            return Result.fail("上传成绩失败");
        }
        return Result.succ(200, "上传成绩成功", null);
    }

    @RequestMapping(value = "/getGradeList", method = RequestMethod.GET)
    @ResponseBody
    public Result getGradeList() {
        String message = null;
        User currentUser = Util.getCurrentUser();
        HashMap<Integer, Map<String, Object>> allStudentGrade;
        if (currentUser.getRole() == 1) {
            try {
                allStudentGrade = gradeService.getAllStudentGrade();
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
        return Result.succ(allStudentGrade);
    }

    @RequestMapping(value = "/getStuGrade", method = RequestMethod.GET)
    @ResponseBody
    public Result getStuGrade() {
        String message = null;
        User currentUser = Util.getCurrentUser();
        HashMap<Integer, Map<String, Object>> stuGrade;
            try {
               stuGrade = gradeService.getStuGrade(currentUser);
            } catch (Exception e) {
                String exception = e.getMessage();
                if (exception.contains("SQLException")) {
                    message = "数据库异常！";
                } else {
                    message = "系统出错！";
                }
                return Result.fail(500, message);
            }
        return Result.succ(stuGrade);
    }
}
