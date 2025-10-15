package com.liluppis.EE_4_exercises.message.service;

import com.liluppis.EE_4_exercises.message.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService{

    private final MessageRepository messageRepository;

    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void createMessage() {

        log.info("Message has been created");
    }
}
