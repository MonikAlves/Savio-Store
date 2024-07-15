package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Repositories.CartRepository;
import ufg.poo.Save.Store.Services.CartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/SavioStore/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService;


    @GetMapping("/all")
    public List<Cart> getAllProducts() {
        return cartRepository.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<List<String>> post(@RequestBody List<Cart> carts){
        List<String> message =  new ArrayList<>();
        for(Cart cart : carts){
            message.add(cartService.addCart(cart));
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}

/*
* calculate_total_product_specific -> dado um id de um produto ele calcula o total em todos os carrinhos que tem o produto
*call SavioStore.calculate_total_product_specific("id produto");
*
* calculate_total_by_id_cart -> dado um id de carrinho ele calcula o total daquele produto especifico
* CALL calculate_total_by_id_cart("id do carrinho");
*
* calculate_total_line -> calcula o total de uma linha quando recebe o id da pessoa e do produto
* call SavioStore.calculate_total_line("id client", "id produto");
*
* get_products_by_id_client -> Recebe o id do usuario e retorna todos os produtos que ele tem no carrinho
* call SavioStore.get_products_by_id_client("id do cliente");
*
*
*
* */
