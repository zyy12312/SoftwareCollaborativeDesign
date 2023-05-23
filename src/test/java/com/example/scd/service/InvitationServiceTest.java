package com.example.scd.service;

import com.example.scd.entity.Invitation;
import com.example.scd.service.impl.InvitationServiceImpl;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InvitationServiceTest {
    InvitationService invitationService = new InvitationServiceImpl();

    @Test
    public void inviteStudent() {
        Integer result = invitationService.inviteStudent(new Invitation(null, 14, 8, 1, LocalDateTime.now(), 2, 2, null));
        assertEquals(new Integer(1),result);
    }

    @Test
    public void acceptInvitation() {
        Integer result = invitationService.acceptInvitation(6);
        assertEquals(new Integer(1),result);

    }

    @Test
    public void rejectInvitation() {
        Integer result = invitationService.rejectInvitation(17);
        assertEquals(new Integer(1),result);
    }

    @Test
    public void showInvitationsOfStudent() {

    }
}
