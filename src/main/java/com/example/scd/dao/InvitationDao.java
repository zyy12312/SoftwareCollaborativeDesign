package com.example.scd.dao;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationDao {
    Integer addInvitation(Invitation invitation);
    Integer updateInvitation(Invitation invitation);
    //修改某学生的所有被拒绝的邀请
    Integer updateInvitationState(Integer studentId,Integer invitationId);
    List<Invitation> showInvitationByStudentId(Integer studentId);
}
