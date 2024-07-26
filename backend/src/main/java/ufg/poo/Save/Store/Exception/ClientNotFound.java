package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Client Not Found Exception
 *
 */
public class ClientNotFound extends SuperException {
    /**
     * Constructor to ClientNotFound
     */
    public ClientNotFound() {
        super("Cliente não encontrado", HttpStatus.NOT_FOUND);
    }
}
