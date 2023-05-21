package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.dao.SubtaskDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Subtask;
import com.example.scd.entity.Task;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class UserDaoImplTest {
    private TeamDao dao = new TeamDaoImpl();
    private SubtaskDao subtaskDao = new SubtaskDaoImpl();

    @Test
    void getAllStudent(){
        subtaskDao.updateSubtask(new Subtask(3, 1, 3, "开发经理", "提交各自负责的类图和协作图", null, LocalDateTime.now(), 3));
    }

}
