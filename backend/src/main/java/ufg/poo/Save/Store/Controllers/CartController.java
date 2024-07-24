package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ufg.poo.Save.Store.DTOS.ErrorDTO;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.SuperException;
import ufg.poo.Save.Store.Services.CartService;

import java.util.List;

@RestController
@RequestMapping("/SavioStore/Cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<?> showProducts(@PathVariable long id) {
        List<Cart> carts;

        try {
            carts = this.cartService.importList(id);
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().body(carts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Cart cart) {
        ErrorDTO a = new ErrorDTO();
        a.setError("Deu certo");
        try{
            this.cartService.addCart(cart);
        }catch (SuperException e) {
            return ResponseDTO.response(e);
        }
        return ResponseEntity.ok().body(a);
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody Cart cart) {
        try{
            this.cartService.buyCart(cart);

        }catch (SuperException e) {
            return ResponseDTO.response(e);
        }
        return ResponseEntity.ok().build();
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
*/