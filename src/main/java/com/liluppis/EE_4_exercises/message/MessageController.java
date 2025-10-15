package com.liluppis.EE_4_exercises.message;

import com.liluppis.EE_4_exercises.message.dto.MessageCreationDTO;
import com.liluppis.EE_4_exercises.message.dto.MessageResponseDTO;
import com.liluppis.EE_4_exercises.message.objectMapper.MessageMapper;
import com.liluppis.EE_4_exercises.message.service.MessageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MessageServiceImpl messageService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageController(MessageServiceImpl messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<MessageResponseDTO>> createNewMessage(
            @Valid @RequestBody MessageCreationDTO messageCreationDTO
    ){
        return messageService.createMessage(messageCreationDTO)
                .map(savedMessage -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(messageMapper.toMessageResponseDTO(savedMessage));

    }
}
