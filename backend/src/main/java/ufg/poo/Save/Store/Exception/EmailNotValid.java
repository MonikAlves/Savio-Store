package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Email Not Valid Exception
 *
 */
public class EmailNotValid extends SuperException {
    /**
     * Constructor to EmailNotValid
     */
    public EmailNotValid() {
        super("Email inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
