package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class EmailNotValid extends SuperException {
    public EmailNotValid() {
        super("Email inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
