package com.example.scd.dao;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationDao {
    Integer addInvitation(Invitation invitation);
    List<Invitation> getInvitationsByStudentId(Integer studentID);
}
