package com.example.scd.dao.impl;

import com.example.scd.dao.UserDao;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());

    @Override
    public List<User> getAllStudent() {
        try{
            return runner.query("select * from Account where role = 0",new BeanListHandler<User>(User.class));
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
