package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class ClientAlreadyExist extends SuperException {
    public ClientAlreadyExist(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
