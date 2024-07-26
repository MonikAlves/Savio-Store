package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Unauthorized Purchase Exception
 *
 */
public class UnauthorizedPurchase extends SuperException {
    /**
     * Constructor to UnauthorizedPurchase
     */
    public UnauthorizedPurchase() {
        super("Compra não autorizada", HttpStatus.UNAUTHORIZED);
    }
}
