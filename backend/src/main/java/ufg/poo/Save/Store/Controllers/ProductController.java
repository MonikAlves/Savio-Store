package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SavioStore/Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }

    @GetMapping("/random")
    public List<Product> random(){
        List<Product> products =  this.productService.getAll();
        return this.productService.getRandom(products);
    }


    @PostMapping("/post")
    public void post(@RequestBody List<Product> product){
        for(Product p : product){
            this.productService.verifyInformationEmpty(p);
            this.productService.saveProduct(p);
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Product product){
        this.productService.delete(product.getId());
    }

}
