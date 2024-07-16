package ufg.poo.Save.Store.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ufg.poo.Save.Store.Exception.*;

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

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity handleUnauthorized(Unauthorized unauthorized) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorized.getMessage());
    }

    @ExceptionHandler(EmailIsNotValid.class)
    public ResponseEntity handleEmailIsNotValid(EmailIsNotValid emailIsNotValid) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(emailIsNotValid.getMessage());
    }
}
