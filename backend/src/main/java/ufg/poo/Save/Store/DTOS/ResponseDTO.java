package ufg.poo.Save.Store.DTOS;

import org.springframework.http.ResponseEntity;
import ufg.poo.Save.Store.Exception.SuperException;

public class ResponseDTO {
    /**
     * @brief Generate an error from exception
     * @param e Exception to be converted to na error
     * @return A response entity to error
     */
    public static ResponseEntity<ErrorDTO> response(SuperException e) {
        ErrorDTO error = new ErrorDTO();
        error.setError(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}

