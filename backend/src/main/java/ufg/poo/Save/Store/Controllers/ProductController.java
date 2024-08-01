package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Services.ProductService;
import ufg.poo.Save.Store.Exception.*;

import java.util.List;

/**
 * Product Controller
 *
 */
@RestController
@RequestMapping("/SavioStore/Product")
@RequiredArgsConstructor
public class ProductController {
    /**
     * Product service to controller
     */
    private final ProductService productService;

    /**
     * Get a list with all available products
     * @return List of all available products
     */
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }

    /**
     * Generate a list with three random products
     * @return List with three random products
     */
    @GetMapping("/random")
    public List<Product> random(){
        List<Product> products = this.productService.getAll();
        return this.productService.getRandom(products);
    }

    /**
     * Unique
     *
     * @param id Product id
     * @return Response entity with operation status
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> unique(@PathVariable long id){
        Product product = new Product();
        try{
            product = this.productService.getProduct(id);
        }catch (SuperException e){
            return ResponseDTO.response(e);
        }
        return ResponseEntity.ok().body(product);
    }

    /**
     * Get a list with all available product ids
     *
     * @return A list with all available product ids
     */
    @GetMapping("/validId")
    public List<Long> getIds(){
        return this.productService.getIds();
    }


    /**
     * Add a list of products
     * @param product List of products that will be added
     * @return Response entity with operation status
     */
    @PostMapping("/add")
    public ResponseEntity<?> post(@RequestBody List<Product> product){
        try {
            for (Product p : product) {
                this.productService.register(p);
            }
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Delete a product
     * @param product Product to be deleted
     * @return Response entity with operation status
     */
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
