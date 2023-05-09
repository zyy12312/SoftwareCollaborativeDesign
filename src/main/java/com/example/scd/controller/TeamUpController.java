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
        List<User> nonTeamStudent = userService.getNonTeamStudent();
        return Result.succ(nonTeamStudent);
    }


    @RequestMapping(value = "/teacher/addTeam",method = RequestMethod.POST)
    public Result addTeam(Team team){
        Integer result = teamService.addTeam(team);
    }

}

