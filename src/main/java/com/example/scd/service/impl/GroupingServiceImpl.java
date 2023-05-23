package com.example.scd.service.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.CharacterDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.dao.impl.AccountDaoImpl;
import com.example.scd.dao.impl.CharacterDaoImpl;
import com.example.scd.dao.impl.TeamDaoImpl;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Team;
import com.example.scd.service.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//checkWhetherLeader()


@Service
public class GroupingServiceImpl implements GroupingService {
    @Autowired
    TeamDao teamDao;
    @Autowired
    CharacterDao characterDao;

    @Override
    public Integer addTeam(Team team) {
//        team.setStudentCharacter(0);
        if(team != null){
            return teamDao.addTeamStudent(team);
        }
        return 0;
    }

    @Override
    public Boolean checkWhetherLeader(Integer studentID) {
        Team studentTeam = teamDao.getStudentTeam(studentID);
        //需要判断studentTeam是否为空
        String characterByCharacterID = characterDao.getCharacterByCharacterID(studentTeam.getStudentCharacter());
//        String characterByCharacterID = characterDao.getCharacterByCharacterID(studentID);
        if (characterByCharacterID.equals("组长")){
            return true;
        }
        return false;

    }

    @Override
    public Boolean checkWeatherSelected(Integer teamID) {
        //???这个方法到底是干啥的QAQ，看方法名不是用来判断学生是否已组队的嘛。。。
        List<Team> teamListByTeamId = teamDao.getTeamListByTeamId(teamID);
        System.out.println(teamListByTeamId.size());
        int characterNum = characterDao.getNumOfCharacter().intValue();
        System.out.println(characterNum);
        if (teamListByTeamId.size() == characterNum){
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer,List<Team>> readTeamList() {
        List<Team> allTeam = teamDao.getAllTeam();
        Map<Integer,List<Team>> lists = new HashMap();
        for (Team t : allTeam){
            Integer teamID = t.getTeamID();
            List<Team> teams = lists.get(teamID);
            if (teams==null){
                teams = new ArrayList<>();
            }
            teams.add(t);
            lists.put(teamID,teams);
        }
        return lists;
    }

    @Override
    public List<Team> showTeam(Integer teamID) {
        return teamDao.getTeamListByTeamId(teamID);
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
