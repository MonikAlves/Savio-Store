package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedPurchase extends SuperException {
    public UnauthorizedPurchase() {
        super("Compra não autorizada", HttpStatus.UNAUTHORIZED);
    }
}
