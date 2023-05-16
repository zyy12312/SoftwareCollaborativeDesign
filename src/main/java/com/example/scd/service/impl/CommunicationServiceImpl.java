package com.example.scd.service.impl;

import com.example.scd.dao.MessageDao;
import com.example.scd.entity.Message;
import com.example.scd.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author lain,aya
 */
@Service
public class CommunicationServiceImpl implements CommunicationService {
    @Autowired
    private MessageDao messageDao;
    @Override
    public Integer senderSendMessage(Message message) {
        message.setSendTime(LocalDateTime.now());
        return messageDao.addNewMessage(message);
    }

    @Override
    public List<Message> getSendMessageList() {
        return messageDao.getMessageList();
    }

    @Override
    public Message getMessageDetail(Integer messageId) {
        return messageDao.getMessageDetail(messageId);
    }
}
