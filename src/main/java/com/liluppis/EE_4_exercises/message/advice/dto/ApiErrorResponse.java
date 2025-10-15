package com.liluppis.EE_4_exercises.message.advice.dto;

import java.util.List;

public record ApiErrorResponse(
        int status,
        String error,
        List<ValidationError> errors
) {
}
