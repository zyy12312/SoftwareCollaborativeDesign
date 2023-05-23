package com.example.scd.dao.impl;

import com.example.scd.dao.AccountDao;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public Integer addUser(User user) {
        Integer res = null;
        try{
            res = runner.update(" insert into Account(account, password,name, role, sex, teamId) values (?,?,?,?,?,?)",
                    user.getAccount(),user.getPassword(),user.getName(),user.getRole(),user.getSex(),user.getTeamId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
       return res;
    }

    @Override
    public List<User> getAllStudent() {

        try{
            return runner.query("select * from Account where role = 0",new BeanListHandler<User>(User.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUser() {
        try{
            return runner.query("select * from Account",new BeanListHandler<User>(User.class));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByAccount(String account) {
        try{
            User user = runner.query("select * from `Account` where account = ?", new BeanHandler<User>(User.class),account);
//            System.out.println(user);
            return user;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Integer id) {
        try{
            return runner.query("select * from Account where id = ?",new BeanHandler<User>(User.class),id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer updateStudent(User student) {
        Integer res = null;

        try{
            res =  runner.update("update Account set password = ?,name = ?,avatarURL = ?,sex = ?,teamId = ? where id = ?",
                    student.getPassword(),student.getName(),student.getAvatarURL(),student.getSex(),student.getTeamId(),student.getId());
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Integer updateStudentTeamId(Integer studentId, Integer teamId) {
        Integer res = null;
        try{
            res = runner.update("update Account set teamId = ? where id = ?",
                    teamId,studentId);
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return res;
    }

//    @Override
//    public List<User> getNonTeamStudent() {
//        try{
//            return runner.query("select * from Account where role = 0 and teamId is null",new BeanListHandler<User>(User.class));
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
}
