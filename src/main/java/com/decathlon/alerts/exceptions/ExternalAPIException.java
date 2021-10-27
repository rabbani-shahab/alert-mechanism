package com.decathlon.alerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExternalAPIException extends Exception {
    private static final long serialVersionUID = 1L;
    public ExternalAPIException(String message){
        super(message);
    }
}
