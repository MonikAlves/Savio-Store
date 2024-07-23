package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends SuperException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}