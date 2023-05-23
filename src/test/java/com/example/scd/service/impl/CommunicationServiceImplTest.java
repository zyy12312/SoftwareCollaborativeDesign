package com.example.scd.service.impl;

import com.example.scd.entity.Message;
import com.example.scd.service.CommunicationService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommunicationServiceImplTest {
    @Autowired
    CommunicationService communicationService ;
    @Test
    void senderSendMessage() {
        Message message = new Message(null,"test send", LocalDateTime.now(),8,1,null);
        Integer integer = communicationService.senderSendMessage(message);
        Assert.assertEquals("sendMessage",1,integer.intValue());
    }

    @Test
    void getSendMessageList() {
        System.out.println(communicationService.getSendMessageList(1));
    }

    @Test
    void getMessageDetail() {
        System.out.println(communicationService.getMessageDetail(1));
    }
}