package com.example.scd.service;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationService {
    //组长发起邀请
    Integer inviteStudent(Invitation invitation);
    //同学接受邀请
    Integer acceptInvitation(Integer invitationID);
    //Integer acceptInvitation(Integer studentID , Integer invitationId)
    //同学拒绝邀请
    Integer rejectInvitation(Integer studentID);
    List<Invitation> showInvitationsOfStudent(Integer studentID);
}
