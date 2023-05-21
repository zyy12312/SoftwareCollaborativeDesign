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
        try{
            runner.update(" insert into Account(account, name, role, sex, teamId) values (?,?,?,?,?)",
                    user.getAccount(),user.getName(),user.getRole(),user.getSex(),user.getTeamId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
       return 1;
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
            return runner.query("select * from Account where account = ?",new BeanHandler<User>(User.class),account);
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
        try{
            return runner.update("update Account set password = ?,name = ?,avatarURL = ?,sex = ?,teamId = ? where id = ?",
                    student.getPassword(),student.getName(),student.getAvatarURL(),student.getSex(),student.getTeamId(),student.getId());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
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
