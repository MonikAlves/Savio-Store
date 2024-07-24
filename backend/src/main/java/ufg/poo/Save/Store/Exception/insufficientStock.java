package ufg.poo.Save.Store.Exception;

import org.springframework.http.HttpStatus;

 public class insufficientStock extends SuperException {
        public insufficientStock(String message) {
            super(message, HttpStatus.METHOD_NOT_ALLOWED);
        }
}
