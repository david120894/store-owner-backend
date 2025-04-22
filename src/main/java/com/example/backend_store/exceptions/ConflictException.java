package com.example.backend_store.exceptions;

import java.io.Serial;

public class ConflictException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public ConflictException(String message){
        super(message);
    }
}
