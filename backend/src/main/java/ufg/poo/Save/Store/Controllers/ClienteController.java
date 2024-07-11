package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Cliente;
import ufg.poo.Save.Store.Entities.Produtos;
import ufg.poo.Save.Store.Repositories.ClienteRepository;
import ufg.poo.Save.Store.Repositories.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/teste/usuario")
@RequiredArgsConstructor
public class ClienteController {

        @Autowired
        ClienteRepository clienteRepository;

        @GetMapping("/pegar/{id}")
        @Transactional
        public List<Produtos> pegar(@PathVariable long id) {
            List<Produtos> produtos = clienteRepository.get_products_by_ID(id);
            return produtos;
        }

        @PostMapping("/colocar")
        public void colocar(@RequestBody Cliente cliente){
            System.out.println(cliente);
            this.clienteRepository.save(cliente);
        }

        @DeleteMapping("/deletar/{id}")
        public void deletar(@PathVariable long a){
            Cliente cliente = this.clienteRepository.findById(long a);
            this.clienteRepository.delete(cliente);
        }

}
