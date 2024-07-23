package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class EmailNotValid extends SuperException {
    public EmailNotValid(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
