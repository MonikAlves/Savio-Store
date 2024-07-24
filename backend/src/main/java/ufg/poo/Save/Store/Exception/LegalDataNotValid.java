

package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * LegalDataNotValid
 */
public class LegalDataNotValid extends SuperException {
    public LegalDataNotValid(String message) {
        // TODO
        // decidir o status http
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
