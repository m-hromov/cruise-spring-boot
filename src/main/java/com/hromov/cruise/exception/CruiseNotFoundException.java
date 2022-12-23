package com.hromov.cruise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CruiseNotFoundException extends RuntimeException {
    public CruiseNotFoundException() {
    }

    public CruiseNotFoundException(String message) {
        super(message);
    }

    public CruiseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CruiseNotFoundException(Throwable cause) {
        super(cause);
    }

    public CruiseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
