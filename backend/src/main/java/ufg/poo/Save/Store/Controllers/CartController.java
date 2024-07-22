package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.CartRepository;
import ufg.poo.Save.Store.Services.CartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SavioStore/Cart")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;


    @GetMapping("/{id}")
    public List<Product> showProducts(@PathVariable long id) {
        return this.cartService.importList(id);
    }

    @PostMapping("/add")
    public ResponseEntity<List<String>> add(@RequestBody List<Cart> carts){
        List<String> message =  new ArrayList<>();
        for(Cart cart : carts){
            message.add(this.cartService.addCart(cart));
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Cart cart){
        this.cartService.delete(cart.getId());
    }



}

/*
* calculate_total_product_specific -> dado um id de um produto ele calcula o total em todos os carrinhos que tem o produto
*call SavioStore.calculate_total_product_specific("id produto");
*
* calculate_total_by_id_cart -> dado um id de carrinho ele calcula o total daquele produto especifico
* CALL calculate_total_by_id_cart("id do carrinho");
*
*
*
*
* */
