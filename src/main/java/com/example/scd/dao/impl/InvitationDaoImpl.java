package com.example.scd.dao.impl;

import com.example.scd.dao.InvitationDao;
import com.example.scd.entity.Invitation;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
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
                    invitation.getState(),invitation.getInviId());
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
            List<Map<String, Object>> mapList = runner.query("select i.id inviId, i.teamID,i.inviteeID,i.inviterID,i.state,i.invitationTime,i.characterID" +
                    ",a.* from Invitation i,Account a where inviteeID=? and i.inviterID=a.id", new MapListHandler(), studentId);
            System.out.println(mapList);
            List<Invitation> invitationList = new ArrayList<>();
            for (Map<String,Object> map: mapList
            ) {
                Invitation invitation = new Invitation();
                User inviter = new User();
                BeanUtils.populate(inviter,map);
                invitation.setInviter(inviter);
                BeanUtils.populate(invitation,map);
                invitationList.add(invitation);
            }
            return invitationList;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }

    @Override
    public Invitation getInvitationById(Integer invitationId) {
        try {
             Invitation invitation = runner.query("select i.id inviId, i.teamID,i.inviteeID,i.inviterID,i.state,i.invitationTime,i.characterID," +
                    "a.* from Invitation i,Account a where i.id = ?", new BeanHandler<Invitation>(Invitation.class), invitationId);
            return invitation;
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
    }
}
