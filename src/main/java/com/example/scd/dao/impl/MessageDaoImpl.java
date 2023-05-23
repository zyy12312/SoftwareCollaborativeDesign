package com.example.scd.dao.impl;

import com.example.scd.dao.MessageDao;
import com.example.scd.entity.Material;
import com.example.scd.entity.Message;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
//    @Override
//    public Integer addNewMessage(Integer senderID) {
//        return null;
//    }
private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
    @Override
    public Integer addNewMessage(Message message) {
        Integer res = null;
        try{
            res = runner.update("insert into Message(detail, sendTime, senderID, teamID) values (?,?,?,?)",
                    message.getDetail(),message.getSendTime(),message.getSenderID(),message.getTeamID());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Message getMessageDetail(Integer senderID) {
        return null;
    }

    @Override
    public List<Message> getMessageList() {

        try{
            return runner.query("select * from Message",new BeanListHandler<Message>(Message.class));
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Message> addnewMessageList() {
        return null;
    }
}
