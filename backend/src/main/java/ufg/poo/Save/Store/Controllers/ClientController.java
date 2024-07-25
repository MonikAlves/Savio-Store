package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Services.ClientService;


@RestController
@RequestMapping("/SavioStore/Client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


    /**
     * @brief Verify client login
     * @param client
     * @return Response entity with operation status
     */
    @PostMapping("/login")
    public ResponseEntity<?> validateLogin(@RequestBody Client client) {
        Client cliente = null;
        try{
            cliente = this.clientService.verifyLogin(client);
        } catch (SuperException e) {
            return ResponseDTO.response(e);
        }
        return ResponseEntity.ok().body(cliente);
    }

    /**
     * @brief Register client
     * @param client Client to be registered
     * @return Response entity with operation status
     */
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody Client client){
        Client cliente = null;
        try{
            this.clientService.verifyInformationEmpty(client);
            cliente = this.clientService.addClient(client);
        } catch (SuperException e) {
             return ResponseDTO.response(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    /**
     * @brief Delete a client
     * @param client Client to be deleted
     * @return Response entity with operation status
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Client client) {
        try {
            this.clientService.delete(client.getId());
        } catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().build();
    }
}
