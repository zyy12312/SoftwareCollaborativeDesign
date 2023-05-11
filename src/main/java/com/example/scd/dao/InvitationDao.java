package com.example.scd.dao;

import com.example.scd.entity.Invitation;

import java.util.List;

public interface InvitationDao {
    Integer addInvitation(Invitation invitation);
    Integer updateInvitation(Invitation invitation);
    List<Invitation> getInvitationsByInviteeId(Integer inviteeID);
}
