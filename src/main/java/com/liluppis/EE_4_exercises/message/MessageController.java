package com.liluppis.EE_4_exercises.message;

import com.liluppis.EE_4_exercises.message.dto.MessageCreationDTO;
import com.liluppis.EE_4_exercises.message.dto.MessageResponseDTO;
import com.liluppis.EE_4_exercises.message.objectMapper.MessageMapper;
import com.liluppis.EE_4_exercises.message.service.MessageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MessageResponseDTO>> findById(@PathVariable Long id) {
        return messageService.findById(id)
                .map(foundMessage -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(messageMapper.toMessageResponseDTO(foundMessage))
                );
    }

    // TODO - continue from ex.6

    @PostMapping("/create")
    public Mono<ResponseEntity<MessageResponseDTO>> createNewMessage(
            @Valid @RequestBody MessageCreationDTO messageCreationDTO
    ){
        return messageService.createMessage(messageCreationDTO)
                .map(savedMessage -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(messageMapper.toMessageResponseDTO(savedMessage)));

    }
}
