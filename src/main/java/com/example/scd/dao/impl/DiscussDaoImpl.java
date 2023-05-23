package com.example.scd.dao.impl;

import com.example.scd.dao.DiscussDao;
import com.example.scd.entity.Discuss;
import com.example.scd.entity.Task;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class DiscussDaoImpl implements DiscussDao {
    private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
//    @Override
//    public List<Discuss> getDiscussList(Integer authorID, Integer id) {
//        return null;
//    }

    @Override
    public List<Discuss> getDiscussList() {
        try{
            return runner.query("select Discuss.* from Discuss",new BeanListHandler<Discuss>(Discuss.class));
        }catch (SQLException e){
            throw new RuntimeException();
        }finally {
            return null;
        }
    }

    @Override
    public Integer addNewDiscuss(Discuss discuss) {
        try{
            runner.update("insert into Discuss (title, detail, authorID,filesURL,discussTime) values (?,?,?,?,?)",
                    discuss.getTitle(),discuss.getDetail(),discuss.getAuthorID(),discuss.getFilesURL(),discuss.getDiscussTime());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            return null;
        }

    }

    @Override
    public Integer deleteDiscuss(Integer id) {
        try{
            runner.update("delete from Discuss where id = ?",id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            return null;
        }
    }

    @Override
    public Integer modifyDiscuss(Discuss discuss) {
        try{
            return runner.update("update Discuss set title = ?,detail = ?,authorID = ?,filesURL = ?,discussTime = ? where id = ?",
                    discuss.getTitle(),discuss.getDetail(),discuss.getAuthorID(),discuss.getFilesURL(),discuss.getDiscussTime(),discuss.getId());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return null;
        }
    }


    @Override
    public Discuss getDiscussDetail(Integer id) {
        try{
            return runner.query("select Discuss.* from Discuss where id = ?",new BeanHandler<Discuss>(Discuss.class),id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            return null;
        }
    }
}
