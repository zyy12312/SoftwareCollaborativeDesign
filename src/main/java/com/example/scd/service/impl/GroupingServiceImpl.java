package com.example.scd.service.impl;

import com.example.scd.dao.CharacterDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Team;
import com.example.scd.service.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupingServiceImpl implements GroupingService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    CharacterDao characterDao;

    @Override
    public Integer addTeam(Team team) {
        return teamDao.addTeamStudent(team);
    }

    @Override
    public Boolean checkWhetherLeader(Integer studentID) {
        return null;
    }

    @Override
    public Boolean checkWeatherSelected(Integer teamID) {
        return null;
    }

    @Override
    public List<Team> readTeamList() {
        return teamDao.getAllTeam();
    }

    @Override
    public List<Team> showTeam(Integer teamID) {
        return teamDao.getTeamListByTeamId(teamID);
    }

    @Override
    public Integer inviteStudent(Invitation invitation) {
        return null;
    }

    @Override
    public Integer acceptInvitation(Invitation invitation) {
        return null;
    }

    @Override
    public Integer rejectInvitation(Invitation invitation) {
        return null;
    }

//    @Override
//    public List<Team> getTeamListByStudentId(Integer studentID) {
//        return getTeamListByStudentId(studentID);
//    }
//
//    @Override
//    public Integer isTeamUp(Integer teamID) {
//        Long sizeOfTeam = teamDao.getSizeOfTeam(teamID);
//        Long numOfCharacter = characterDao.getNumOfCharacter();
//        if(sizeOfTeam == numOfCharacter){
//            return 1;
//        }else{
//            return 0;
//        }
//    }
}
