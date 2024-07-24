package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.CartRepository;
import ufg.poo.Save.Store.Repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public void addCart(Cart newCart) throws ClientNotFound, ProductNotFound, insufficientStock, SizeNotFound {

            long clientId = newCart.getClient().getId();
            long productId = newCart.getProduct().getId();
            String size = newCart.getSize();


            this.clientService.clientExist(clientId);
            this.productService.productExist(productId);

            Boolean exist =  cartRepository.verify_cart_exist(clientId,productId,size);

            if(!exist){
                this.cartRepository.save(newCart);
                this.cartRepository.calculate_total_line(clientId, productId,size);
            }else{
                Cart cart = this.cartRepository.get_cart_by_id_client_and_id_product(clientId, productId,size);
                if(verifyStock(cart,newCart.getQuantity())){
                    cart.setQuantity(newCart.getQuantity() + cart.getQuantity());
                     this.cartRepository.save(cart);
                    cart.setTotal(cart.getQuantity() *cart.getProduct().getPrice());
                }
            }
    }

    public boolean verifyStock(Cart cart,int quantity) throws SizeNotFound, insufficientStock {
        int stock = Integer.parseInt(this.calculateStockBySize(cart.getProduct().getSize(),cart.getSize(), cart.getProduct().getStock()));
        if(this.cartRepository.verify_stock_cart(cart.getId(),quantity,stock)){
            return true;
        }
        throw new insufficientStock("Estoque insuficiente");

    }

    public String calculateStockBySize(String sizes,String size, String stocks) throws SizeNotFound {
        for(int i=0;i<3;i++){
            if(sizes.split("-")[i].equals(size)){
                return stocks.split("-")[i];
            }
        }
        throw new SizeNotFound("Tamanho solicitado não encontrado");

    }

    public List<Product> importList(long id) throws ClientNotFound {
        this.clientService.clientExist(id);
        return  this.productRepository.get_products_by_id_client(id);
    }

    public void cartExist(long id) throws CartNotFound {
        boolean exist = this.cartRepository.existsById(id);
        if(!exist) throw new CartNotFound("Carrinho não achado");
    }

    public void delete(long id) throws CartNotFound {
        this.cartExist(id);
        this.cartRepository.deleteById(id);
    }
}
