package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.CartRepository;
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

    /**
     * @brief Add a new cart with validations
     * @param newCart New cart to be added
     * @throws ClientNotFound
     * @throws ProductNotFound
     * @throws insufficientStock
     * @throws SizeNotFound
     */
    public void addCart(Cart newCart) throws ClientNotFound, ProductNotFound, insufficientStock, SizeNotFound {
        if(newCart.getQuantity() < 1) throw new SizeNotFound();

        long clientId = newCart.getClient().getId();
        long productId = newCart.getProduct().getId();
        String size = newCart.getSize();

        this.clientService.clientExist(clientId);
        this.productService.productExist(productId);

        boolean exist =  cartRepository.verify_cart_exist(clientId,productId,size);

        if(!exist){
            Product product = productRepository.getProductById(productId);

            int maximum = Integer.parseInt(this.calculateStockBySize(product.getSize(),newCart.getSize(),product.getStock()));

            if(newCart.getQuantity() > maximum) throw new insufficientStock();

            this.cartRepository.save(newCart);
            this.cartRepository.calculate_total_line(clientId, productId,size);
        }else{
            Cart cart = this.cartRepository.get_cart_by_id_client_and_id_product(clientId, productId,size);
            if(verifyStock(cart,newCart.getQuantity())){
                cart.setQuantity(newCart.getQuantity() + cart.getQuantity());
                this.cartRepository.save(cart);
                cart.setTotal(cart.getQuantity() * cart.getProduct().getPrice());
            }
        }
    }

    /**
     * @brief Verify if there is sufficient stock
     * @param cart Cart to be verified
     * @param quantity Quantity of product in cart
     * @return If verification is successful
     * @throws SizeNotFound
     * @throws insufficientStock
     */
    public boolean verifyStock(Cart cart,int quantity) throws SizeNotFound, insufficientStock {
        int stock = Integer.parseInt(this.calculateStockBySize(cart.getProduct().getSize(),cart.getSize(), cart.getProduct().getStock()));
        if(this.cartRepository.verify_stock_cart(cart.getId(),quantity,stock)){
            return true;
        }
        throw new insufficientStock();

    }

    /**
     * @brief Calculate the stock from size string information
     * @param sizes String with available sizes
     * @param size String with chosen size
     * @param stocks String with stock values
     * @return A string with stock value
     * @throws SizeNotFound
     */
    public String calculateStockBySize(String sizes, String size, String stocks) throws SizeNotFound {
        for(int i=0;i<3;i++){
            if(sizes.split("-")[i].equals(size)){
                return stocks.split("-")[i];
            }
        }
        throw new SizeNotFound();

    }

    /**
     * @brief Get list with client carts
     * @param id Client id
     * @return A list with all client carts
     * @throws ClientNotFound
     */
    public List<Cart> importList(long id) throws ClientNotFound {
        this.clientService.clientExist(id);
        return this.cartRepository.get_products_by_id_client(id);
    }

    /**
     * @brief Verify if cart exists by id
     * @param id Cart id
     * @throws CartNotFound
     */
    public void cartExist(long id) throws CartNotFound {
        boolean exist = this.cartRepository.existsById(id);
        if(!exist) throw new CartNotFound();
    }

    /**
     * @brief Delete cart from id
     * @param id Cart id
     * @throws CartNotFound
     */
    public void delete(long id) throws CartNotFound {
        this.cartExist(id);
        this.cartRepository.deleteById(id);
    }

    /**
     * @brief Reduce cart stock
     * @param cart Cart whose stock will be reduced
     * @return String with new stocks
     * @throws ProductNotFound
     */
    public String reduceStock(Cart cart) throws ProductNotFound {
        this.productService.productExist(cart.getProduct().getId());
        String sizes = cart.getProduct().getSize();
        String[] stock = cart.getProduct().getStock().split("-");
        for(int i=0;i<3;i++){
            if(sizes.split("-")[i].equals(cart.getSize())){
                stock[i] = String.valueOf(Integer.parseInt(stock[i]) - cart.getQuantity());
            }
        }

        return stock[0] +"-"+ stock[1] +"-"+ stock[2];
    }

    /**
     * @brief Make cart purchase
     * @param cart Cart to be purchased
     * @throws ClientNotFound
     * @throws ProductNotFound
     * @throws insufficientStock
     * @throws SizeNotFound
     * @throws CartNotFound
     */
    public void buyCart(Cart cart) throws ClientNotFound, ProductNotFound, insufficientStock, SizeNotFound, CartNotFound, UnauthorizedPurchase {
        if (!cart.getAvailable()) {
            throw new UnauthorizedPurchase();
        }

        cart = this.cartRepository.getReferenceById(cart.getId());
        long clientId = cart.getClient().getId();
        long productId = cart.getProduct().getId();

        this.clientService.clientExist(clientId);
        this.productService.productExist(productId);
        this.cartExist(cart.getId());

        this.verifyStock(cart, 0);

        cart.getProduct().setStock(this.reduceStock(cart));

        String size = cart.getProduct.getSize();
        int stock = Integer.parseInt(this.calculateStockBySize(size, cart.getSize(), cart.getProduct().getStock()));

        this.cartRepository.verify_purchase_available(productId, size, stock);
        this.cartRepository.delete(cart);
    }

    /**
     * @brief Make purchase for all carts
     * @param id Cliente id
     * @throws ClientNotFound 
     * @throws UnauthorizedPurchase
     */
    public void buyAllCart(long id) throws ClientNotFound, ProductNotFound, insufficientStock, SizeNotFound, CartNotFound, UnauthorizedPurchase {
        List<Cart> carts = this.importList(id);

        for (Cart cart: carts) {
            this.buyCart(cart);
        }
    }
}
