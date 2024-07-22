package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.ProductRepository;
import ufg.poo.Save.Store.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SavioStore/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    @Transactional
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/random")
    public List<Product> random(){
        List<Product> products =  productService.getAll();
        return productService.getRandom(products);
    }

    @PostMapping("/post")
    public void post(@RequestBody List<Product> product){
        for(Product p : product){
            productService.verifyInformationEmpty(p);
            productService.saveProduct(p);
        }
    }

//    @DeleteMapping("/delete")
//    public void delete(@RequestBody Product product){
//        System.out.println(product);
//        this.productRepository.delete(product);
//    }

    //delete só precisa do pk, o id

}
