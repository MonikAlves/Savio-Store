package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Cart Not Found Exception
 *
 */
public class CartNotFound extends SuperException {
    /**
     * Constructor to CartNotFound
     *
     */
    public CartNotFound() {
        super("Carrinho não encontrado", HttpStatus.NOT_FOUND);
    }
}
