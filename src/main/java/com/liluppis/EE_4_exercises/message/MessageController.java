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

    /* <ResponseEntity<MessageResponseDTO>> vs <ResponseEntity<Message>>
    *
    *   NEVER return the actual Entity object in ResponseEntity
    *   This can create som serious security issues, since you're returning the entire object,
    *   containing sensitive information like 'password' or Personally Identifiable Information.
    *
    *   ALWAYS use a DTO class to map the Entity Object, so that you can hide sensitive information
    *   that a user does not need to see.
    * */

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MessageResponseDTO>> findById(@PathVariable Long id) {
        return messageService.findById(id)
                .map(foundMessage -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(messageMapper.toMessageResponseDTO(foundMessage))
                );
    }

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
