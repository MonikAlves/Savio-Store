package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class LegalDataNotValid extends SuperException {
    public LegalDataNotValid() {
        super("CPF inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
