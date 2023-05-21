package com.example.scd.service;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationService {
    //组长发起邀请
    Integer inviteStudent(Invitation invitation);
    //同学接受邀请
    Integer acceptInvitation(Invitation invitation);
    //Integer acceptInvitation(Integer studentID , Integer invitationId)
    //同学拒绝邀请
    Integer rejectInvitation(List<Integer> invitationIds);
    List<Invitation> showInvitationsOfStudent(Integer studentID);
}
