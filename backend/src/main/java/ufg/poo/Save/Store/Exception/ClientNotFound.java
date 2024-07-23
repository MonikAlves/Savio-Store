package ufg.poo.Save.Store.Exception;


import org.springframework.http.HttpStatus;

public class ClientNotFound extends SuperException {
    public ClientNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
