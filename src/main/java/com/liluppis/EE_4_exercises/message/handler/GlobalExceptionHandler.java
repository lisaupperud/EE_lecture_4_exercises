package com.liluppis.EE_4_exercises.message.handler;

import com.liluppis.EE_4_exercises.message.MessageNotFoundException;
import com.liluppis.EE_4_exercises.message.handler.dto.ApiErrorResponse;
import com.liluppis.EE_4_exercises.message.handler.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleMessageNotFound(MessageNotFoundException ex){

        log.warn(ex.getMessage());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }
}
