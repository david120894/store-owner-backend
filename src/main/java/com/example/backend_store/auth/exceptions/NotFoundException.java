package com.example.backend_store.auth.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public NotFoundException(String message){
        super(message);
    }
}
