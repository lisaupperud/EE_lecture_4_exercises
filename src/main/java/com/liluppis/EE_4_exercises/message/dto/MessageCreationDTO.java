package com.liluppis.EE_4_exercises.message.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/* Creation DTO
*   Purpose: Input from Client
*   Q: Who sets the values?
*   A: The User (through an API call)
* */

public record MessageCreationDTO(
        @Size(min = 3, max = 255, message = "Check the message length!")
        @NotBlank(message = "Message can't be blank or contain whitespaces")
        String message,
        boolean pinned
) {
}
