package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

public class SizeNotFound extends SuperException {
    public SizeNotFound() {
        super("Tamanho indisponível", HttpStatus.NOT_FOUND);
    }
}
