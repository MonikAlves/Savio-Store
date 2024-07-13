package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/SavioStore/product")
@RequiredArgsConstructor
public class ProductController {

    ProductRepository productRepository;

    @GetMapping("/all")
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/post")
    public void post(@RequestBody List<Product> product){
        for(Product p : product){
            productRepository.save(p);
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Product product){
        System.out.println(product);
        this.productRepository.delete(product);
    }

    //delete só precisa do pk, o id

}
