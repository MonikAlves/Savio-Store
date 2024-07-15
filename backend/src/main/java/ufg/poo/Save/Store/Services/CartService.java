package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.CartRepository;
import ufg.poo.Save.Store.Repositories.ClientRepository;
import ufg.poo.Save.Store.Repositories.ProductRepository;

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

            clientService.clientExist(clientId);
            productService.productExist(productId);

            String exist = cartRepository.verify_cart_exist(clientId,productId);

            if(exist.equals("no")){
                cartRepository.save(cart);
                cartRepository.calculate_total_line(clientId, productId);
                return "Adicionado no carrinho\n";
            }else{
                return "Adicionado mais uma unidade ao carrinho\n";
            }
    }

    public List<Product> importList(long id){
        clientService.clientExist(id);
        return  this.productRepository.get_products_by_id_client(id);
    }
}
