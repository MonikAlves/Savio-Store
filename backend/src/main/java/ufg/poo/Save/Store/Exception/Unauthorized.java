package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class Unauthorized extends SuperException {
    public Unauthorized() {
        super("Senha incorreta", HttpStatus.UNAUTHORIZED);
    }
}
