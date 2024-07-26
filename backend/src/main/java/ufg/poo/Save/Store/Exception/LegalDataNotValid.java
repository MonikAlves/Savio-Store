package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

/**
 * Legal Data Not Valid Exception
 *
 */
public class LegalDataNotValid extends SuperException {
    /**
     * Constructor to LegaDataNotValid
     */
    public LegalDataNotValid() {
        super("CPF inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
