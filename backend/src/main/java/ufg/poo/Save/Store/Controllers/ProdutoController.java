package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Cliente;
import ufg.poo.Save.Store.Entities.Produtos;
import ufg.poo.Save.Store.Repositories.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/SavioStore/produto")
@RequiredArgsConstructor
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/todos")
    @Transactional
    public List<Produtos> pegar() {
        return produtoRepository.findAll();
    }

    @PostMapping("/post")
    public void post(@RequestBody List<Produtos> produto){
        for(Produtos p : produto){
            produtoRepository.save(p);
        }
    }

    @DeleteMapping("/deleta")
    public void colocar(@RequestBody Produtos produto){
        System.out.println(produto);
        this.produtoRepository.delete(produto);
    }

    //delete só precisa do pk, o id

}
