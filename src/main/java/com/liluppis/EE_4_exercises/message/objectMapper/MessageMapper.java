package com.liluppis.EE_4_exercises.message.objectMapper;

import com.liluppis.EE_4_exercises.message.Message;
import com.liluppis.EE_4_exercises.message.dto.MessageCreationDTO;
import com.liluppis.EE_4_exercises.message.dto.MessageResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapper {

    public Message toMessage(MessageCreationDTO dto) {
        return new Message(
                null,
                dto.message(),
                LocalDateTime.now(),
                dto.pinned()
        );
    }

    public MessageResponseDTO toMessageResponseDTO(Message message) {
        return new MessageResponseDTO(
                message.id(),
                message.message(),
                message.createdAt(),
                message.pinned()
        );
    }
}
