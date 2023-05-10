package com.example.scd.controller;

import com.example.scd.entity.Invitation;
import com.example.scd.entity.Result;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.InvitationService;
import com.example.scd.service.TeamService;
import com.example.scd.service.UserService;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamUpController {
//    @Autowired
//    InvitationService invitationService;
//    @GetMapping("/team_up")
//    public List<Invitation> getInvitation(@RequestParam() Integer studentId){
//        System.out.println(studentId);
//        return invitationService.showInvitationsOfStudent(studentId);
//    }


    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/teacher/addTeam",method = RequestMethod.GET)
    public Result showLeaderCandidate(){
        List<User> allStudent = userService.getAllStudent();
        return Result.succ(allStudent);
    }


    @RequestMapping(value = "/teacher/addTeam",method = RequestMethod.POST)
    public Result addTeam(@RequestParam("team") Team team,@RequestParam("student") String student){

        System.out.println(team);
        System.out.println(student);
//        Object o = map.get("team");
//        Team a = JACKSON.readValue(JACKSON.writeValueAsString(Object), Team.class);
//        System.out.println(team);
//        System.out.println(student);
//        Integer result1 = teamService.addTeam(team);
//        Integer result2 = userService.updateStudent(student);
//        if(result1 == 1 && result2 == 1){
//            return Result.succ(200,"添加成功！",null);
//        }
        return Result.fail("添加失败！");
    }


}

