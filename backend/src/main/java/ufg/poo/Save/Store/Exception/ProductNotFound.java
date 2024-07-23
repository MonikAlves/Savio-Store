package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class ProductNotFound extends SuperException {
    public ProductNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
