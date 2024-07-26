package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to Bad Request
 *
 */
public class BadRequestException extends SuperException {
    /**
     * Constructor to Bad Request
     *
     * @param message Message exception
     */
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
