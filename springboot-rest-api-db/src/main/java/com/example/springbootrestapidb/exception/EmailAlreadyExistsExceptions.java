package com.example.springbootrestapidb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsExceptions extends RuntimeException{

    private String message;

    public EmailAlreadyExistsExceptions(String message){
        super(message);
        this.message = message;
    }
}
