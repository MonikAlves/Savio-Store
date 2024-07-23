package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class SuperException extends Exception {
    private final HttpStatus status;

    public SuperException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
