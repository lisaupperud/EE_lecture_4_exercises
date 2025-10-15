package com.liluppis.EE_4_exercises.message.advice;

import com.liluppis.EE_4_exercises.message.advice.dto.ApiErrorResponse;
import com.liluppis.EE_4_exercises.message.advice.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

@RestControllerAdvice
public class ValidationAdvice {

    private static final Logger log = LoggerFactory.getLogger(ValidationAdvice.class);

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(WebExchangeBindException ex) {

        List<ValidationError> errorDetailList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream().map(
                        fieldError -> new ValidationError(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                ).toList();

        log.warn("Validation failed {} -> errors {}",
                errorDetailList.size(),
                errorDetailList.stream().map(ValidationError::field)
                        .toList());

        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        errorDetailList
                )
        );
    }
}
