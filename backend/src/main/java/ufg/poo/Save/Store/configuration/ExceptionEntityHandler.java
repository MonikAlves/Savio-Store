package ufg.poo.Save.Store.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ufg.poo.Save.Store.Exception.ClientAlreadyExist;
import ufg.poo.Save.Store.Exception.ClientNotFound;
import ufg.poo.Save.Store.Exception.ProductNotFound;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(ClientAlreadyExist.class)
    public ResponseEntity handleClientAlreadyExist(ClientAlreadyExist clientAlreadyExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(clientAlreadyExist.getMessage());
    }

    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity handleClientNotFound(ClientNotFound clientNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientNotFound.getMessage());
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity handleProductNotFound(ProductNotFound productNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFound.getMessage());
    }
}
