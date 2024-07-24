package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class ClientNotFound extends SuperException {
    public ClientNotFound() {
        super("Cliente não encontrado", HttpStatus.NOT_FOUND);
    }
}
