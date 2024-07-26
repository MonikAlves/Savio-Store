package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * insufficient Stock Exception
 *
 */
public class insufficientStock extends SuperException {
    /**
     * Constructor to insufficientStock
     */
    public insufficientStock() {
        super("Estoque insuficiente", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
