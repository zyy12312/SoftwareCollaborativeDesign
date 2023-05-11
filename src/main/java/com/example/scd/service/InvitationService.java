package com.example.scd.service;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationService {
    Integer addInvitation(Invitation invitation);
    Integer updateInvitation(Invitation invitation);
    List<Invitation> showInvitationsOfStudent(Integer studentID);
}
