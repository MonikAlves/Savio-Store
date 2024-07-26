package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Product Not Found Exception
 *
 */
public class ProductNotFound extends SuperException {
    /**
     * Constructor to ProductNotFound
     */
    public ProductNotFound() {
        super("Produto não encontrado", HttpStatus.NOT_FOUND);
    }
}
