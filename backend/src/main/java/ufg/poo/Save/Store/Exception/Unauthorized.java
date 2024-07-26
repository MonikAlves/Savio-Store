package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Unauthorized Exception
 *
 */
public class Unauthorized extends SuperException {
    /**
     * Constructor to Unauthorized
     */
    public Unauthorized() {
        super("Senha incorreta", HttpStatus.UNAUTHORIZED);
    }
}
