package com.example.scd.dao.impl;

import com.example.scd.dao.MessageDao;
import com.example.scd.entity.Material;
import com.example.scd.entity.Message;
import com.example.scd.entity.User;
import com.example.scd.utils.C3p0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Message> getMessageList(Integer teamID) {
        try{
            List<Map<String, Object>> mapList = runner.query("select a.*,m.id mid,m.detail,m.sendTime,m.senderID,m.teamID from Message m,Account a where m.senderID = a.id and m.teamID = ?",new MapListHandler(),teamID);
            List<Message> messageList = new ArrayList<>();
            for (Map<String,Object> map: mapList
            ) {
                Message message = new Message();
                User sender = new User();
                BeanUtils.populate(sender,map);
                message.setSender(sender);
                BeanUtils.populate(message,map);
                messageList.add(message);
            }
            return messageList;
        }catch (SQLException e){
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Message> addnewMessageList() {
        return null;
    }
}
