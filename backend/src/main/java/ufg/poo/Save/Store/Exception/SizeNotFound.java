package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class SizeNotFound extends SuperException {
    public SizeNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
