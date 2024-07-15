package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.ClientNotFound;
import ufg.poo.Save.Store.Exception.ProductNotFound;
import ufg.poo.Save.Store.Repositories.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public String getProduto(){
        return this.productRepository.toString();
    }


    public boolean register(Product novoproduto){
        this.productRepository.save(novoproduto);
        return true;
    }

    public void productExist(long id){
        boolean exist = this.productRepository.existsById(id);
        if(!exist) throw new ProductNotFound("Product not found");
    }

}
