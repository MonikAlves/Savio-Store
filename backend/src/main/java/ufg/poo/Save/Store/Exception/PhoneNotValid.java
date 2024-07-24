package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class PhoneNotValid extends SuperException {
    public PhoneNotValid() {
        super("Telefone inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
