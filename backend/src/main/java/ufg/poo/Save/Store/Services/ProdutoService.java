package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private ProductRepository productRepository;

    public String getProduto(){
        return this.productRepository.toString();
    }


    public boolean register(Product novoproduto){
        this.productRepository.save(novoproduto);
        return true;
    }

}
