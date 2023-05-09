package com.example.scd.service.impl;

import com.example.scd.dao.InvitationDao;
import com.example.scd.dao.impl.InvitationDaoImpl;
import com.example.scd.entity.Invitation;
import com.example.scd.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {
    private InvitationDao invitationDao = new InvitationDaoImpl();
    @Override
    public List<Invitation> showInvitationsOfStudent(Integer studentID) {
        return invitationDao.getInvitationsByStudentId(studentID);
    }
}
