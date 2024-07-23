package ufg.poo.Save.Store.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import ufg.poo.Save.Store.Exception.SuperException;

public class ResponseDTO {
    public static ResponseEntity<ErrorDTO> response(SuperException e) {
        ErrorDTO error = new ErrorDTO();
        error.setError(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}

