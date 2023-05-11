package com.example.scd.service.impl;

import com.example.scd.dao.CharacterDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Character;
import com.example.scd.entity.Team;
import com.example.scd.service.TeamService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Result;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    CharacterDao characterDao;

    @Override
    public Integer addTeam(Team team) {
        return teamDao.addTeamStudent(team);
    }

    @Override
    public List<Team> getAllTeam() {
        return teamDao.getAllTeam();
    }

    @Override
    public List<Team> getTeamListByTeamId(Integer teamID) {
        return teamDao.getTeamListByTeamId(teamID);
    }

    @Override
    public List<Team> getTeamListByStudentId(Integer studentID) {
        return getTeamListByStudentId(studentID);
    }

    @Override
    public Integer isTeamUp(Integer teamID) {
        Long sizeOfTeam = teamDao.getSizeOfTeam(teamID);
        Long numOfCharacter = characterDao.getNumOfCharacter();
        if(sizeOfTeam == numOfCharacter){
            return 1;
        }else{
            return 0;
        }
    }
}
