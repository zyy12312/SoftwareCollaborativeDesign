package com.example.scd.dao.impl;

import com.example.scd.dao.MessageDao;
import com.example.scd.entity.Message;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MessageDaoImplTest {

    MessageDao messageDao = new MessageDaoImpl();

    @Test
    void addNewMessage() {
        Integer result = messageDao.addNewMessage(new Message(null, "hello", LocalDateTime.now(), 9, 1, null));
        assertEquals(new Integer(1),result);

    }

    @Test
    void getMessageDetail() {
        List<Message> messageList = messageDao.getMessageList(1);
        System.out.println(messageList);
    }

    @Test
    void getMessageList() {
    }

    @Test
    void addnewMessageList() {
    }
}
