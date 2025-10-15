package com.liluppis.EE_4_exercises.message.service;

import com.liluppis.EE_4_exercises.message.Message;
import com.liluppis.EE_4_exercises.message.MessageRepository;
import com.liluppis.EE_4_exercises.message.dto.MessageCreationDTO;
import com.liluppis.EE_4_exercises.message.objectMapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements IMessageService{

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,  MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public Mono<Message> findById(Long id) {

        log.info("Found Message with id: {}", id);

        return messageRepository.findById(id);
    }

    @Override
    public Mono<Message> createMessage(MessageCreationDTO messageCreationDTO) {

        log.info("New Message has been created & saved");

        return messageRepository.save(
                messageMapper.toMessage(messageCreationDTO)
        );
    }
}
