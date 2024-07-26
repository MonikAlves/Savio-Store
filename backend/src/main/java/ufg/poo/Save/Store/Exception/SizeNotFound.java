package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Size Not Found Exception
 *
 */
public class SizeNotFound extends SuperException {
    /**
     * Constructor to SizeNotFound
     */
    public SizeNotFound() {
        super("Tamanho indisponível", HttpStatus.NOT_FOUND);
    }
}
