package com.liluppis.EE_4_exercises.message;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("""
                Message with Id %s not found
                """.formatted(id)
        );
    }
}
