package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Repositories.CartRepository;

@Service
@RequiredArgsConstructor
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public String addCart(@RequestBody Cart cart){
            long clientId = cart.getClient().getId();
            long productId = cart.getProduct().getId();
            String exist = cartRepository.verify_cart_exist(clientId,productId);

            if(exist.equals("no")){
                cartRepository.save(cart);
                cartRepository.calculate_total_line(clientId, productId);
                return "Adicionado no carrinho\n";
            }else{
                return "Adicionado mais uma unidade ao carrinho\n";
            }
    }
}
