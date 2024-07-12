package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/teste/usuario")
@RequiredArgsConstructor
public class ClientController {

        @Autowired
        ClientRepository clientRepository;

        @GetMapping("/pegar/{id}")
        @Transactional
        public List<Product> pegar(@PathVariable long id) {
            List<Product> produtos = clientRepository.get_products_by_ID(id);
            return produtos;
        }

        @PostMapping("/colocar")
        public void colocar(@RequestBody Client client){
            System.out.println(client);
            this.clientRepository.save(client);
        }

        @DeleteMapping("/deletar/{id}")
        public void deletar(@PathVariable Client client){
            this.clientRepository.delete(client);
        }

}
