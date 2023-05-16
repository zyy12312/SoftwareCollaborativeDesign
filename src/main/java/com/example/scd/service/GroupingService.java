package com.example.scd.service;

import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface GroupingService {
    //读取所有小组的信息
    Map<Integer,List<Team>> readTeamList();
    //读取某个小组的信息
    List<Team> showTeam(Integer teamID);
//    List<Team> showTeam(Integer studentID);
    //组长发起邀请
    Integer inviteStudent(Invitation invitation);
    //同学接受邀请
    Integer acceptInvitation(Invitation invitation);
    //Integer acceptInvitation(Integer studentID , Integer invitationId)
    //同学拒绝邀请
    Integer rejectInvitation(Invitation invitation);
    //添加同学在小组中的信息
    Integer addTeam(Team team);
    //是否为小组长
    Boolean checkWhetherLeader(Integer studentID);
    //是否完成组队
    Boolean checkWeatherSelected(Integer teamID);
}
