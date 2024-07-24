package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class CartNotFound extends SuperException {
    public CartNotFound() {
        super("Carrinho não encontrado", HttpStatus.NOT_FOUND);
    }
}
