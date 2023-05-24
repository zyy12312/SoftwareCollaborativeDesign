package com.example.scd.dao.impl;

import com.example.scd.dao.InvitationDao;
import com.example.scd.entity.Invitation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvitationDaoImplTest {
    InvitationDao invitationDao = new InvitationDaoImpl();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void addInvitation() {
        Invitation invitation = new Invitation(null, 8, 13, 1, LocalDateTime.parse("2023-05-22 10:14:07", dateTimeFormatter), 1, 4, null);
        Integer integer = invitationDao.addInvitation(invitation);
        Assert.assertEquals("add inviation" , 1,integer.intValue());
    }

    @Test
    void updateInvitation() {
        Invitation invitation = new Invitation(5, 8, 13, 0, LocalDateTime.parse("2023-05-22 10:14:07", dateTimeFormatter), 1, 4, null);
        Integer integer = invitationDao.updateInvitation(invitation);
        Assert.assertEquals("edit inviation" , 1,integer.intValue());
    }

    @Test
    void updateInvitationState() {
        Integer integer = invitationDao.updateInvitationState(13);
        Assert.assertEquals("edit inviation" , 1,integer.intValue());

    }

    @Test
    void showInvitationByStudentId() {
        List<Invitation> invitations = invitationDao.showInvitationByStudentId(15);
        System.out.println(invitations);
    }

    @Test
    void getInvitationById() {
        Invitation invitation = new Invitation(5, 8, 13, 2, LocalDateTime.parse("2023-05-23 18:10:58", dateTimeFormatter), 1, 4, null);
        Invitation invitationById = invitationDao.getInvitationById(5);
        Assert.assertEquals("invitation error",invitation,invitationById);
    }
}
