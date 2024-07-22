package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.ClientNotFound;
import ufg.poo.Save.Store.Repositories.CartRepository;
import ufg.poo.Save.Store.Repositories.ClientRepository;
import ufg.poo.Save.Store.Repositories.ProductRepository;
import ufg.poo.Save.Store.Exception.CartNotFound;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public String addCart(Cart cart){

            long clientId = cart.getClient().getId();
            long productId = cart.getProduct().getId();

            this.clientService.clientExist(clientId);
            this.productService.productExist(productId);

            String exist = cartRepository.verify_cart_exist(clientId,productId);

            if(exist.equals("no")){
                this.cartRepository.save(cart);
                this.cartRepository.calculate_total_line(clientId, productId);
                return "Adicionado no carrinho\n";
            }else{
                return "Adicionado mais uma unidade ao carrinho\n";
            }
    }

    public List<Product> importList(long id){
        this.clientService.clientExist(id);
        return  this.productRepository.get_products_by_id_client(id);
    }

    public void cartExist(long id){
        boolean exist = this.cartRepository.existsById(id);
        if(!exist) throw new CartNotFound("Cart not found");
    }

    public void delete(long id){
        this.cartExist(id);
        this.cartRepository.deleteById(id);
    }
}
