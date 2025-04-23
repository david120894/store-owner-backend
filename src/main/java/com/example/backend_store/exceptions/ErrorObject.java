package com.example.backend_store.exceptions;

import lombok.Data;

import java.util.Date;

@Data

public class ErrorObject {
    private String message;
    private Integer statusCode;
    private Date timestamp;
}
