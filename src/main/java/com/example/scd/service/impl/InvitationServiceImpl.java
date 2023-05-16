package com.example.scd.service.impl;

import com.example.scd.dao.InvitationDao;
import com.example.scd.dao.impl.InvitationDaoImpl;
import com.example.scd.entity.Invitation;
import com.example.scd.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    InvitationDao invitationDao;

    @Override
    public Integer addInvitation(Invitation invitation) {
        return invitationDao.addInvitation(invitation);
    }

    @Override
    public Integer updateInvitation(Invitation invitation) {
        return invitationDao.updateInvitation(invitation);
    }

    @Override
    public List<Invitation> showInvitationsOfStudent(Integer inviteeID) {
        return invitationDao.showInvitationByStudentId(inviteeID);
    }
}
