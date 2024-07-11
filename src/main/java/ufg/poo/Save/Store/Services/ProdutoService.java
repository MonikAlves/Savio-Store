package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufg.poo.Save.Store.Entities.Produtos;
import ufg.poo.Save.Store.Repositories.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public String getProduto(){
        return this.produtoRepository.toString();
    }


    public boolean register(Produtos novoproduto){
        this.produtoRepository.save(novoproduto);
        return true;
    }

}
