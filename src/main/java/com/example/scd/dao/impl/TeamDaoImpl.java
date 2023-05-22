package com.example.scd.dao.impl;

import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Team;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 没有将Team中的User查询
 */
@Repository
public class TeamDaoImpl implements TeamDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    public Integer addTeamStudent(Team team){
        try{
            runner.update("insert into Team(teamID, studentID, studentCharacter) values (?,?,?)",
                    team.getTeamID(),team.getStudentID(),team.getStudentCharacter());
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public List<Team> getAllTeam() {
        try {
            return runner.query("select t.*,c.`character` from" +
                    " Team t,`Character` c where  t.studentCharacter = c.id",
                    new BeanListHandler<Team>(Team.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getTeamByStudentId(Integer studentID) {
       List<Team> teamList = new ArrayList<>();
        try {
            return runner.query("select t.*,c.`character` from" +
                            " Team t,`Character` c where t.studentID = ? and t.studentCharacter = c.id",
                    new BeanListHandler<Team>(Team.class),studentID);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getTeamListByTeamId(Integer teamID) {
        try {
            return runner.query("select t.*,c.`character` from" +
                            " Team t,`Character` c where t.teamID = ? and t.studentCharacter = c.id order by t.teamID",
                    new BeanListHandler<Team>(Team.class),teamID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getSizeOfTeam(Integer teamID) {
        try {
            return runner.query("select count(*) from Team where teamID = ?",new ScalarHandler<>(),teamID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Team getStudentTeam(Integer studentID) {
        try {
            return runner.query("select * from Team where studentID = ?",new BeanHandler<Team>(Team.class),studentID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
