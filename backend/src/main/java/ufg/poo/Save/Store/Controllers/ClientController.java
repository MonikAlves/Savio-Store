package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.ClientRepository;
import ufg.poo.Save.Store.Services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/SavioStore/Client")
@RequiredArgsConstructor
public class ClientController {

        private final ClientService clientService;

        @PostMapping("/login")
        public Client validateLogin(@RequestBody Client client) {
            System.out.println("Deu certo " + client);
            return clientService.verifyLogin(client);
        }

        @PostMapping("/register")
        public ResponseEntity<String> colocar(@RequestBody Client client){
            String message = clientService.addClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }

        /*@DeleteMapping("/deletar/{id}")
        public void deletar(@PathVariable Client client){
            this.clientRepository.delete(client);
        }*/

}
