package com.liluppis.EE_4_exercises.message.service;

import com.liluppis.EE_4_exercises.message.Message;
import com.liluppis.EE_4_exercises.message.dto.MessageCreationDTO;
import reactor.core.publisher.Mono;

public interface IMessageService {

    Mono<Message> findById(Long id);
    Mono<Message> createMessage(MessageCreationDTO messageCreationDTO);
    Mono<Void> deleteMessageById(Long id);

}
