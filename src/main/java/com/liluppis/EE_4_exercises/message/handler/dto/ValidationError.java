package com.liluppis.EE_4_exercises.message.handler.dto;

public record ValidationError(
        String field,
        String message
) {
}
