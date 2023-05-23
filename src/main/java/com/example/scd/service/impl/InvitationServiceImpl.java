package com.example.scd.service.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.InvitationDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.dao.impl.InvitationDaoImpl;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.InvitationService;
import com.example.scd.utils.Util;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    InvitationDao invitationDao = new InvitationDaoImpl();
    @Autowired
    AccountDao accountDao;
    @Autowired
    TeamDao teamDao;

    @Override
    public Integer inviteStudent(Invitation invitation) {
        if(invitation != null){
            return invitationDao.addInvitation(invitation);
        }
        return 0;
    }

    @Override
    public Integer acceptInvitation(Integer invitationId) {
        Invitation invitation= invitationDao.getInvitationById(invitationId);
        //修改邀请信息
        Integer result1 = invitationDao.updateInvitation(invitation);
        User stu = Util.getCurrentUser();
        stu.setTeamId(invitation.getTeamID());
        //修改学生基本信息
        Integer result2 = accountDao.updateStudent(stu);
        //添加小组内身份信息
        Integer result3 = teamDao.addTeamStudent(new Team(null,invitation.getTeamID(),stu.getId(),invitation.getCharacterID(),null,null));
        if(result1 == 1 && result2 == 1 && result3 == 1){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer rejectInvitation(List<Integer> invitationIds) {
        User currentUser = Util.getCurrentUser();
        Integer result = new Integer(0);
        for (Integer invitationId:
             invitationIds) {
            result += invitationDao.updateInvitationState(currentUser.getId(), invitationId);
        }
        if(result == invitationIds.size()){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public List<Invitation> showInvitationsOfStudent(Integer inviteeID) {
        return invitationDao.showInvitationByStudentId(inviteeID);
    }
}
