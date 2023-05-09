package com.example.scd.dao.impl;

import com.example.scd.dao.InvitationDao;
import com.example.scd.entity.Invitation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvitationDaoImplTest {
    private InvitationDao invitationDao = new InvitationDaoImpl();
    @Test
    void addInvitation() {
         invitationDao.addInvitation(new Invitation(null,1,2,0, LocalDateTime.now(),null));
    }

    @Test
    void getInvitationsByStudentId() {
        List<Invitation> invitationsByStudentId = invitationDao.getInvitationsByStudentId(2);
        System.out.println(invitationsByStudentId);
    }
}
