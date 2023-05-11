package com.example.scd.controller;

import com.example.scd.entity.Invitation;
import com.example.scd.entity.Result;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.InvitationService;
import com.example.scd.service.TeamService;
import com.example.scd.service.UserService;
import com.example.scd.utils.GsonGenerator;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamUpController {

    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    InvitationService invitationService;

    /**
     * 获取学生列表
     */
    @RequestMapping(value = "/studentList",method = RequestMethod.GET)
    public Result showLeaderCandidate(){
        List<User> allStudent = userService.getAllStudent();
        return Result.succ(allStudent);
    }

    /**
     * 教师端--创建小组
     */
    @RequestMapping(value = "/teacher/addTeam",method = RequestMethod.POST)
    public Result addTeam(@RequestBody Map<String, Object> map){
        Object team = map.get("team");
        Gson gson = GsonGenerator.gsonSetter();
        String s = gson.toJson(team);
        Team team1 = gson.fromJson(s, Team.class);
        Object user = map.get("student");
        s = gson.toJson(user);
        User user1 = gson.fromJson(s, User.class);
        Integer result1 = teamService.addTeam(team1);
        Integer result2 = userService.updateStudent(user1);
        if(result1 == 1 && result2 == 1){
            return Result.succ(200,"添加成功！",null);
        }
        return Result.fail(500,"添加失败！",null);
    }

    /**
     * 教师端--获取组队信息
     */
    @RequestMapping(value = "teacher/teamInfo",method = RequestMethod.GET)
    public Result getTeamsInfo(){
        List<Map<String,Object>> teamInfo = new ArrayList<>();
        List<Team> allTeam = teamService.getAllTeam();
       Map<Integer, List<Team>> listMap = allTeam.stream().collect(Collectors.groupingBy(Team::getTeamID));
        for (Map.Entry   entry:
              listMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("team",entry.getValue());
            Integer isTeamUp = teamService.isTeamUp((Integer) entry.getKey());
            map.put("isTeamUp",isTeamUp);
            teamInfo.add(map);
        }
        return Result.succ(teamInfo);
    }

    /**
     * 学生端--组队页面
     * @return 若已组队，返回小组信息
     * 若未组队，返回邀请列表
     */
    @GetMapping("/student")
    public Result getTeamInfo(){
        //获取当前登录用户信息的方法待定
        User student = new User(2,"2011110102","123456","王",null,0,null,2);  //假定为当前登录用户
        if (student.getTeamId() != null){
            List<Team> teamInfo = teamService.getTeamListByTeamId(student.getTeamId());
            return Result.succ(teamInfo);
        }else{
            List<Invitation> invitationList = invitationService.showInvitationsOfStudent(student.getId());
            return Result.succ(invitationList);
        }
    }

    /**
     * 学生端--发起组队邀请
     */
    @RequestMapping(value = "student/invitation",method = RequestMethod.POST)
    public Result sendInvitation(@RequestBody Invitation invitation){
        Integer result = invitationService.addInvitation(invitation);
        if(result == 1){
            return Result.succ(200,"发起邀请成功！",null);
        }else{
            return Result.fail("发起邀请失败！");
        }
    }

    /**
     * 学生端--处理组队邀请
     */
    @RequestMapping(value = "student/invitation",method = RequestMethod.PUT)
    public Result dealInvitation(@RequestBody Invitation invitation){
        Integer result = invitationService.updateInvitation(invitation);
        //获取当前学生信息，修改其teamID，调用userService修改
        if(result == 1){
            return Result.succ(null);
        }else{
            return Result.fail("操作失败！");
        }
    }




}

