package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Produtos;
import ufg.poo.Save.Store.Repositories.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/pegar/{id}")
    @Transactional
    public List<Produtos> pegar(@PathVariable long id) {
        List<Produtos> produtos = produtoRepository.get_products_by_ID(id);
        return produtos;
    }

    @DeleteMapping("/colocar")
    public void colocar(@RequestBody Produtos produto){
        System.out.println(produto);
        this.produtoRepository.delete(produto);
    }

    //delete só precisa do pk, o id

}
