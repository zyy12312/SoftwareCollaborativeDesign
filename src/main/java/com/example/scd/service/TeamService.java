package com.example.scd.service;

import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Integer addTeam(Team team);
    List<Team> getAllTeam();
    List<Team> getTeamListByTeamId(Integer teamID);
    List<Team> getTeamListByStudentId(Integer studentID);
    Integer isTeamUp(Integer teamID);
}
