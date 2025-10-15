package com.liluppis.EE_4_exercises.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("messages")
public record Message(
        @Id
        Long id,
        String message,
        @Column("created_at")
        LocalDateTime createdAt
) {
}
