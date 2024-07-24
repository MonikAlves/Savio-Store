package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class ProductNotFound extends SuperException {
    public ProductNotFound() {
        super("Produto não encontrado", HttpStatus.NOT_FOUND);
    }
}
