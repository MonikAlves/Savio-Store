package ufg.poo.Save.Store.DTOS;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String error;
}