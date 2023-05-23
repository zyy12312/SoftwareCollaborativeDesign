package com.example.scd.service;

import com.example.scd.entity.Invitation;
import com.example.scd.service.impl.InvitationServiceImpl;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class InvitationServiceTest {
    InvitationService invitationService = new InvitationServiceImpl();

    @Test
    public void inviteStudent() {
        invitationService.inviteStudent( new Invitation(null,14,8,null, LocalDateTime.now(),2,2,null));
    }

    @Test
    public void acceptInvitation() {
    }

    @Test
    public void rejectInvitation() {
    }

    @Test
    public void showInvitationsOfStudent() {
    }
}
