package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Task;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserDaoImplTest {
    private TeamDao dao = new TeamDaoImpl();
    @Test
    void getAllStudent(){
        System.out.println(dao.addTeamStudent(new Team(2,1,1,1,"a",null)));
    }

}
