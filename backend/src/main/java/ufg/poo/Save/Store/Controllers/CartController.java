package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.SuperException;
import ufg.poo.Save.Store.Services.CartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SavioStore/Cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<?> showProducts(@PathVariable long id) {
        List<Product> products;

        try {
            products = this.cartService.importList(id);
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody List<Cart> carts) {
        // TODO
        List<String> message = new ArrayList<>();

        for (Cart cart : carts) {
            message.add(this.cartService.addCart(cart));
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Cart cart){
        try {
            this.cartService.delete(cart.getId());
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().build();
    }
}

/*
* calculate_total_product_specific -> dado um id de um produto ele calcula o total em todos os carrinhos que tem o produto
*call SavioStore.calculate_total_product_specific("id produto");
*
* calculate_total_by_id_cart -> dado um id de carrinho ele calcula o total daquele produto especifico
* CALL calculate_total_by_id_cart("id do carrinho");
*
* */
