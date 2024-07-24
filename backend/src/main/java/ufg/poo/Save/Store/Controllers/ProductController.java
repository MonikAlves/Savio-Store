package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Services.ProductService;
import ufg.poo.Save.Store.Exception.*;

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
        List<Product> products = this.productService.getAll();
        return this.productService.getRandom(products);
    }

    @PostMapping("/add")
    public ResponseEntity<?> post(@RequestBody List<Product> product){
        try {
            for (Product p : product) {
                this.productService.verifyInformationEmpty(p);
                this.productService.saveProduct(p);
            }
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Product product) {
        try {
            this.productService.delete(product.getId());
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().build();
    }
}