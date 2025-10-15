package com.liluppis.EE_4_exercises.message.dto;

import java.time.LocalDateTime;

/* Response DTO
 *   Purpose: Output to Client
 *   Q: Who sets the values?
 *   A: The Server (by logic & DB)
 * */

public record MessageResponseDTO(
        Long id,
        String message,
        LocalDateTime createdAt,
        boolean pinned
) {
}
