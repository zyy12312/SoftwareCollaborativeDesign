package com.example.scd.controller;

import com.example.scd.entity.Invitation;
import com.example.scd.entity.Result;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.InvitationService;
import com.example.scd.service.GroupingService;
import com.example.scd.service.UserService;
import com.example.scd.utils.GsonGenerator;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/team")
@CrossOrigin
public class TeamUpController {

    @Autowired
    GroupingService groupingService;
    @Autowired
    UserService userService;
    @Autowired
    InvitationService invitationService;

//    /**
//     * 获取学生列表
//     */
//    @RequestMapping(value = "/studentList",method = RequestMethod.GET)
//    @ResponseBody
//    public Result showLeaderCandidate(){
//        List<User> allStudent = null;
//        try {
//            allStudent = userService.getAllStudent();
//        } catch (Exception e) {
//            String message = e.getMessage();
//            if(message.contains("SQLException")){
//
//            }
//        }
//        return Result.succ(allStudent);
//    }

    /**
     * 教师端--创建小组
     */
    @RequestMapping(value = "/teacher/addTeam",method = RequestMethod.POST)
    @ResponseBody
    public Result addTeam(@RequestBody Map<String, Object> map){
        Object team = map.get("team");
        Gson gson = GsonGenerator.gsonSetter();
        String s = gson.toJson(team);
        Team team1 = gson.fromJson(s, Team.class);
        Object user = map.get("student");
        s = gson.toJson(user);
        User user1 = gson.fromJson(s, User.class);
        String message = null;
        Integer result1 = null;
        Integer result2 = null;
        try {
            result1 = groupingService.addTeam(team1);
            result2 = userService.updateUser(user1);
        } catch (Exception e) {
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        if(result1==null||result1==0){
            return Result.fail(500,"创建小组失败！");
        }
        if(result2==null||result2==0){
            return Result.fail(500,"更新用户信息失败！");
        }
        return Result.succ(200,"创建成功！",null);
    }

    /**
     * 教师端--获取组队信息
     */
    @RequestMapping(value = "/teacher/teamInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result getTeamsInfoForTeacher(){
        List<Map<String,Object>> teamInfo = new ArrayList<>();
        Map<Integer, List<Team>> groupMap = groupingService.readTeamList();
        if(groupMap.size() == 0){
            return Result.succ(null);
        }
//       Map<Integer, List<Team>> listMap = allTeam.stream().collect(Collectors.groupingBy(Team::getTeamID));
        for (Map.Entry  entry:
              groupMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("team",entry.getValue());
            Boolean isTeamUp = groupingService.checkWeatherSelected((Integer) entry.getKey());
            map.put("isTeamUp",isTeamUp);
            teamInfo.add(map);
        }
        return Result.succ(teamInfo);
    }

    /**
     * 学生端--获取小组信息
     * @return
     */
    @GetMapping("/student")
    @ResponseBody
    public Result getTeamInfoForStudent(){
        //获取当前登录用户信息的方法待定
        User student = new User();
        if (student.getId() != null){
            List<Team> teamInfo = groupingService.showTeam(student.getTeamId());
            return Result.succ(teamInfo);
        }
        return Result.fail("用户信息有误，无法获取信息！");
    }

    //获取邀请列表
    @GetMapping("/student/invitation")
    @ResponseBody
    public Result getInvitationList(){
        //获取当前登录用户信息的方法待定
        User student = new User();  //假定为当前登录用户
        if(student.getId() != null){
            List<Invitation> invitationList = invitationService.showInvitationsOfStudent(student.getId());
            return Result.succ(invitationList);
        }
        return Result.fail("用户信息有误，无法获取信息！");
    }

    /**
     * 学生端--发起组队邀请
     */
    @RequestMapping(value = "/student/invitation",method = RequestMethod.POST)
    @ResponseBody
    public Result sendInvitation(@RequestBody Invitation invitation){
        Integer result = null;
        String message = null;
        try{
            result = invitationService.inviteStudent(invitation);
        }catch (Exception e){
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        if(result == null || result == 0){
            return Result.fail(500,"发送邀请失败！");
        }
        return Result.succ(200,"发起邀请成功！",null);
    }

    /**
     * 学生端--接受组队邀请
     */
    @RequestMapping(value = "/student/acceptInvitation",method = RequestMethod.PUT)
    @ResponseBody
    public Result acceptInvitation(@RequestBody Invitation invitation){
        Integer result = null;
        String message = null;
        try{
//            result = invitationService.rejectInvitation();
        }catch (Exception e){
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        if(result == null || result == 0){
            return Result.fail("接受邀请失败！");
        }else{
            return Result.succ(200,"接受邀请成功",null);
        }
    }

    /**
     * 学生端--拒绝组队邀请
     */
    @RequestMapping(value = "/student/rejectInvitation",method = RequestMethod.PUT)
    @ResponseBody
    public Result rejectInvitation(@RequestBody List<Integer> invitationIds){
        Integer result = null;
        String message = null;
        try{
            result = invitationService.rejectInvitation(invitationIds);
        }catch (Exception e){
            String exception = e.getMessage();
            if(exception.contains("SQLException")){
                message = "数据库异常！";
            }else{
                message = "系统出错！";
            }
            return Result.fail(500,message);
        }
        if(result == null || result == 0){
            return Result.fail("拒绝邀请失败！");
        }else{
            return Result.succ(200,"已拒绝所有邀请！",null);
        }
    }
}

