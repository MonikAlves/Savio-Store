package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ufg.poo.Save.Store.DTOS.ErrorDTO;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.SuperException;
import ufg.poo.Save.Store.Services.CartService;

import java.util.List;

@RestController
@RequestMapping("/SavioStore/Cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     * @brief Get a list with all available products in client cart
     * @param id Client id
     * @return Response entity with status operation and product list
     */
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

    /**
     * @brief Add cart
     * @param cart Cart to be added
     * @return Response entity with status operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Cart cart) {
        try {
            this.cartService.addCart(cart);
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        // TODO
        // verificar se isso é necessário
        ErrorDTO error = new ErrorDTO();
        error.setError("Deu certo");

        return ResponseEntity.ok().body(error);
    }

    /**
     * @brief Make cart purchase
     * @param cart Cart to be purchased
     * @return Response entity with status operation
     */
    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody Cart cart) {
        try {
            this.cartService.buyCart(cart);
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * @brief Make purchase for all client carts
     * @param client Client
     * @return Response entity with status operation
     */
    @PostMapping("/buyAll")
    public ResponseEntity<?> buyAll(@RequestBody Client client) {
        try {
            this.cartService.buyAllCart(client.getId());
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().build();
    }

    /**
     * @brief Delete a cart
     * @param cart Cart to be deleted
     * @return Response entity with status operation
     */
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
