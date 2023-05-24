package com.example.scd.dao.impl;

import com.example.scd.dao.TeamDao;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 没有将Team中的User查询
 */
@Repository
public class TeamDaoImpl implements TeamDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    public Integer addTeamStudent(Team team){
        Integer res = null;
        try{
            res = runner.update("insert into Team(teamID, studentID, studentCharacter) values (?,?,?)",
                    team.getTeamID(),team.getStudentID(),team.getStudentCharacter());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public List<Team> getAllTeam() {
        try {
            return runner.query("select t.*,c.`character` studentCharacterLabel, " +
                            "u.id uid,u.account,u.name uname,u.avatarURL,u.role,u.sex from" +
                            " Team t,`Character` c,Account u where  t.studentCharacter = c.id",
                    new ResultSetHandler<List<Team>>() {
                        @Override
                        public List<Team> handle(ResultSet rs) throws SQLException {
                            List<Team> teams = new ArrayList<>();
                            Map<Integer, Team> teamMap = new HashMap<>();
                            while (rs.next()) {
                                int teamId = rs.getInt("id");
                                Team team = teamMap.get(teamId);
                                if (team == null) {
                                    team = new Team();
                                    team.setId(teamId);
                                    team.setStudentID(rs.getInt("studentID"));
                                    team.setStudentCharacter(rs.getInt("studentCharacter"));
                                    team.setStudentCharacterLabel(rs.getString("studentCharacterLabel"));
                                    int userId = rs.getInt("uid");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("uname"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        team.setUser(user);
                                    }
//                                    User user = new User();
//                                    team.setStudent(user);
                                    teams.add(team);
                                    teamMap.put(teamId, team);
                                }


                            }
                            return teams;
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getTeamByStudentId(Integer studentID) {
       List<Team> teamList = new ArrayList<>();
        try {
            return runner.query("select t.*,c.`character` studentCharacterLabel, " +
                    "u.id uid,u.account,u.name uname,u.avatarURL,u.role,u.sex from" +
                            " Team t,`Character` c,Account u where t.studentID = ? and t.studentCharacter = c.id",
                    new ResultSetHandler<List<Team>>() {
                        @Override
                        public List<Team> handle(ResultSet rs) throws SQLException {
                            List<Team> teams = new ArrayList<>();
                            Map<Integer, Team> teamMap = new HashMap<>();
                            while (rs.next()) {
                                int teamId = rs.getInt("id");
                                Team team = teamMap.get(teamId);
                                if (team == null) {
                                    team = new Team();
                                    team.setId(teamId);
                                    team.setStudentID(rs.getInt("studentID"));
                                    team.setStudentCharacter(rs.getInt("studentCharacter"));
                                    team.setStudentCharacterLabel(rs.getString("studentCharacterLabel"));
                                    int userId = rs.getInt("uid");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("uname"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        team.setUser(user);
                                    }
//                                    User user = new User();
//                                    team.setStudent(user);
                                    teams.add(team);
                                    teamMap.put(teamId, team);
                                }


                            }
                            return teams;
                        }
                    },studentID);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getTeamListByTeamId(Integer teamID) {
        try {
            return runner.query("select t.*,c.`character` studentCharacterLabel, " +
                    "u.id uid,u.account,u.name uname,u.avatarURL,u.role,u.sex from" +
                            " Team t,`Character` c,Account u where t.teamID = ? and t.studentCharacter = c.id order by t.teamID",
                    new ResultSetHandler<List<Team>>() {
                        @Override
                        public List<Team> handle(ResultSet rs) throws SQLException {
                            List<Team> teams = new ArrayList<>();
                            Map<Integer, Team> teamMap = new HashMap<>();
                            while (rs.next()) {
                                int teamId = rs.getInt("id");
                                Team team = teamMap.get(teamId);
                                if (team == null) {
                                    team = new Team();
                                    team.setId(teamId);
                                    team.setStudentID(rs.getInt("studentID"));
                                    team.setStudentCharacter(rs.getInt("studentCharacter"));
                                    team.setStudentCharacterLabel(rs.getString("studentCharacterLabel"));
                                    int userId = rs.getInt("uid");
                                    if (userId != 0) {
                                        User user = new User();
                                        user.setId(userId);
                                        user.setName(rs.getString("uname"));
                                        user.setAccount(rs.getString("account"));
                                        user.setSex(rs.getInt("sex"));
                                        user.setRole(rs.getInt("role"));
                                        user.setAvatarURL(rs.getString("avatarURL"));
                                        team.setUser(user);
                                    }
//                                    User user = new User();
//                                    team.setStudent(user);
                                    teams.add(team);
                                    teamMap.put(teamId, team);
                                }


                            }
                            return teams;
                        }
                    },teamID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getSizeOfTeam(Integer teamID) {
        Long res = null;
        try {
            res = runner.query("select count(*) from Team where teamID = ?",new ScalarHandler<>(),teamID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Team getStudentTeam(Integer studentID) {
        try {
            return runner.query("select t.*,c.`character` studentCharacterLabel, " +
                            "u.id uid,u.account,u.name uname,u.avatarURL,u.role,u.sex from" +
                            " Team t,`Character` c,Account uwhere studentID = ?",
                    new ResultSetHandler<Team>() {
                        @Override
                        public Team handle(ResultSet rs) throws SQLException {
                            int teamId = rs.getInt("id");
                            Team team;
                            team = new Team();
                            team.setId(teamId);
                            team.setStudentID(rs.getInt("studentID"));
                            team.setStudentCharacter(rs.getInt("studentCharacter"));
                            team.setStudentCharacterLabel(rs.getString("studentCharacterLabel"));
                            int userId = rs.getInt("uid");
                            if (userId != 0) {
                                User user = new User();
                                user.setId(userId);
                                user.setName(rs.getString("uname"));
                                user.setAccount(rs.getString("account"));
                                user.setSex(rs.getInt("sex"));
                                user.setRole(rs.getInt("role"));
                                user.setAvatarURL(rs.getString("avatarURL"));
                                team.setUser(user);
                            }

                            return team;
                        }
                    },studentID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
