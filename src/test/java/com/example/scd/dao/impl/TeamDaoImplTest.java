package com.example.scd.dao.impl;

import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Team;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TeamDaoImplTest {

    TeamDao teamDao = new TeamDaoImpl();
    @Test
    public void addTeamStudent() {
        Integer result = teamDao.addTeamStudent(new Team(null, 1, 12, 5, null, null));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void getAllTeam() {
        List<Team> allTeam = teamDao.getAllTeam();
        System.out.println(allTeam);
    }

    @Test
    public void getTeamByStudentId() {
        List<Team> teamByStudentId = teamDao.getTeamByStudentId(8);
        System.out.println(teamByStudentId);
    }

    @Test
    public void getTeamListByTeamId() {
        List<Team> teamListByTeamId = teamDao.getTeamListByTeamId(1);
        System.out.println(teamListByTeamId);
    }

    @Test
    public void getSizeOfTeam() {
        Long sizeOfTeam = teamDao.getSizeOfTeam(1);
        assertEquals(new Long(5),sizeOfTeam);
    }

    @Test
    public void getStudentTeam() {
       //不需要的样子
    }
}
