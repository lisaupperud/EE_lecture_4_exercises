package com.liluppis.EE_4_exercises.message;

import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("messages")
public record Message(
        Long id,
        String message,
        LocalDateTime createdAt
) {
}
