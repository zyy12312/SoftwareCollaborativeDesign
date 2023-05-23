package com.example.scd.service;

import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Team;
import com.example.scd.service.impl.GroupingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class GroupingServiceTest {
    GroupingService groupingService = new GroupingServiceImpl();

    @Test
    public void readTeamList() {
        Map<Integer, List<Team>> integerListMap = groupingService.readTeamList();
        System.out.println(integerListMap);
    }

    @Test
    public void showTeam() {
        List<Team> teamList = groupingService.showTeam(3);
        System.out.println(teamList);
    }

    @Test
    public void addTeam() {
        Integer result = groupingService.addTeam(null);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void checkWhetherLeader() {
        Boolean aBoolean = groupingService.checkWhetherLeader(9);
        assertEquals(false,aBoolean);

    }

    @Test
    public void checkWeatherSelected() {
        Boolean aBoolean = groupingService.checkWeatherSelected(1);
        assertEquals(true,aBoolean);
    }
}
