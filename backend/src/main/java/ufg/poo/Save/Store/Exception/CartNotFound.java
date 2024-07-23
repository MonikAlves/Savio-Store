package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class CartNotFound extends SuperException {
    public CartNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}