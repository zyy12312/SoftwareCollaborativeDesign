package com.example.scd.service;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationService {
    List<Invitation> showInvitationsOfStudent(Integer studentID);
}
