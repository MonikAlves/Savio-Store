package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Phone Not Valid Exception
 *
 */
public class PhoneNotValid extends SuperException {
    /**
     * Constructor to Phone Not Valid
     */
    public PhoneNotValid() {
        super("Telefone inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
