package com.example.backend_store.auth.exceptions;

import lombok.Data;

import java.util.Date;

@Data

public class ErrorObject {
    private String message;
    private Integer statusCode;
    private Date timestamp;
    private String Details;
}
