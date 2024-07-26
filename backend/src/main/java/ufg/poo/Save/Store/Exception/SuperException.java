package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Super Exception
 *
 * All exceptions inherit from it
 */
public class SuperException extends Exception {
    /**
     * Status http
     */
    private final HttpStatus status;

    /**
     * Constructor to SuperException
     *
     * @param message Exception message
     * @param status Status http
     */
    public SuperException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Get http status
     *
     * @return Http status
     */
    public HttpStatus getStatus() {
        return status;
    }
}
