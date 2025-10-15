package com.liluppis.EE_4_exercises.message.advice.dto;

public record ValidationError(
        String field,
        String message
) {
}
