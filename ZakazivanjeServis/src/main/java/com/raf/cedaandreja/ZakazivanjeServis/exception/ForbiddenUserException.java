package com.raf.cedaandreja.ZakazivanjeServis.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenUserException extends CustomException{

    public ForbiddenUserException(String message) {
        super(message, ErrorCode.FORBIDDEN_USER, HttpStatus.BAD_REQUEST);
    }
}
