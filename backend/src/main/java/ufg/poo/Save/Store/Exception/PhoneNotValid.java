package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class PhoneNotValid extends SuperException {
    public PhoneNotValid(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
