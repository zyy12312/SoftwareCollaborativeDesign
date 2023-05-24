package com.example.scd.dao.impl;

import com.example.scd.dao.InvitationDao;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InvitationDaoImpl implements InvitationDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public Integer addInvitation(Invitation invitation) {
        Integer res = null;
        try {
            //执行插入sql
            res = runner.update("insert into Invitation (inviterID,inviteeID,`state`,invitationTime,teamID,characterID) values (?,?,?,?,?,?)",
                    invitation.getInviterID(),invitation.getInviteeID(),invitation.getState(),invitation.getInvitationTime(),
                    invitation.getTeamID(),invitation.getCharacterID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer updateInvitation(Invitation invitation) {
        Integer res = null;
        try {
            //执行插入sql
            res = runner.update("update Invitation set state = ? where id = ?",
                    invitation.getState(),invitation.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer updateInvitationState(Integer studentId) {
        Integer res = null;
        try {
            //执行插入sql
            res = runner.update("update Invitation set state = 2 where inviteeID = ? and state = 0",
                    studentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public List<Invitation> showInvitationByStudentId(Integer studentId) {
        try {
            List<Invitation> invitations = runner.query("select i.id inviId, i.teamID,i.inviteeID,i.inviterID,i.state,i.invitationTime,i.characterID" +
                            ",a.* from Invitation i,Account a where inviteeID=? and i.inviterID=a.id",
                    (ResultSetHandler<List<Invitation>>) rs -> {
                        List<Invitation> invitationList = new ArrayList<>();
                        while (rs.next()) {
                            Invitation invitation = new Invitation();
                            invitation.setId(rs.getInt("inviId"));
                            invitation.setInviterID(rs.getInt("inviterID"));
                            invitation.setInviteeID(rs.getInt("inviteeID"));
                            String invitationTime = rs.getString("invitationTime");
                            invitation.setInvitationTime(LocalDateTime.parse(invitationTime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                            invitation.setTeamID(rs.getInt("teamID"));
                            invitation.setCharacterID(rs.getInt("characterID"));
                            invitation.setState(rs.getInt("state"));
                            User inviter = new User();
                            inviter.setId(rs.getInt("id"));
                            inviter.setName(rs.getString("name"));
                            inviter.setAccount(rs.getString("account"));
                            inviter.setSex(rs.getInt("sex"));
                            inviter.setRole(rs.getInt("role"));
                            inviter.setAvatarURL(rs.getString("avatarURL"));
                            invitation.setInviter(inviter);
                            invitationList.add(invitation);
                        }
                        return invitationList;
                    }, studentId);
            return invitations;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Invitation getInvitationById(Integer invitationId) {
        try {
             Invitation result = runner.query("select i.id inviId, i.teamID,i.inviteeID,i.inviterID,i.state,i.invitationTime,i.characterID," +
                    "a.* from Invitation i,Account a where i.id = ?", (ResultSetHandler<Invitation>) rs -> {
                 Invitation invitation = new Invitation();
                 while (rs.next()) {
                     invitation.setId(rs.getInt("inviId"));
                     invitation.setInviterID(rs.getInt("inviterID"));
                     invitation.setInviteeID(rs.getInt("inviteeID"));
                     String invitationTime = rs.getString("invitationTime");
                     invitation.setInvitationTime(LocalDateTime.parse(invitationTime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                     invitation.setTeamID(rs.getInt("teamID"));
                     invitation.setCharacterID(rs.getInt("characterID"));
                     invitation.setState(rs.getInt("state"));
                     User inviter = new User();
                     inviter.setId(rs.getInt("id"));
                     inviter.setName(rs.getString("name"));
                     inviter.setAccount(rs.getString("account"));
                     inviter.setSex(rs.getInt("sex"));
                     inviter.setRole(rs.getInt("role"));
                     inviter.setAvatarURL(rs.getString("avatarURL"));
                     invitation.setInviter(inviter);
                 }
                 return invitation;
             }, invitationId);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }
}
