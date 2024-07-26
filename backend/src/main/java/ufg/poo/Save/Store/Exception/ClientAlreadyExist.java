package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Client Already Exist Exception
 *
 */
public class ClientAlreadyExist extends SuperException {
    /**
     * Constructor to ClientAlreadyExist
     */
    public ClientAlreadyExist() {
        super("Cliente já está cadastrado", HttpStatus.CONFLICT);
    }
}
