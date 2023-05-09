package com.example.scd.dao;

import com.example.scd.entity.Team;

import java.util.List;

public interface TeamDao {
    Integer addTeamStudent(Team team);
    List<Team> getAllTeam();
    List<Team> getTeamByStudentId(Integer studentID);
    List<Team> getTeamListByTeamId(Integer teamID);
    Integer getSizeOfTeam(Integer teamID);
}
