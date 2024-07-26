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

/**
 * Cart Service
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final PurchaseService purchaseService;

    /**
     * Add a new cart with validations
     * @param newCart New cart to be added
     * @throws ClientNotFound If the client does not exist
     * @throws ProductNotFound If the product does not exist
     * @throws insufficientStock If the product stock is insufficient
     * @throws SizeNotFound If the product size does not exist
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
            newCart.setAvailable(true);
            this.cartRepository.save(newCart);
            this.cartRepository.calculate_total_line(clientId, productId,size);
        }else{
            Cart cart = this.cartRepository.get_cart_by_id_client_and_id_product(clientId, productId,size);
            if(verifyStock(cart,newCart.getQuantity())){
                cart.setQuantity(newCart.getQuantity() + cart.getQuantity());
                newCart.setAvailable(true);
                this.cartRepository.save(cart);
                cart.setTotal(cart.getQuantity() * cart.getProduct().getPrice());
            }
        }
    }

    /**
     * Verify if there is sufficient stock
     * @param cart Cart to be verified
     * @param quantity Quantity of product in cart
     * @return If verification is successful
     * @throws SizeNotFound If the product size does not exist
     * @throws insufficientStock If the product stock is insufficient
     */
    public boolean verifyStock(Cart cart,int quantity) throws SizeNotFound, insufficientStock {
        int stock = Integer.parseInt(this.calculateStockBySize(cart.getProduct().getSize(),cart.getSize(), cart.getProduct().getStock()));
        if(this.cartRepository.verify_stock_cart(cart.getId(),quantity,stock)){
            return true;
        }
        cart.setAvailable(false);
        throw new insufficientStock();

    }

    /**
     * Calculate the stock from size string information
     * @param sizes String with available sizes
     * @param size String with chosen size
     * @param stocks String with stock values
     * @return A string with stock value
     * @throws SizeNotFound If the size product does not exist
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
     * Get list with client carts
     * @param id Client id
     * @return A list with all client carts
     * @throws ClientNotFound If client does not exist
     */
    public List<Cart> importList(long id) throws ClientNotFound {
        this.clientService.clientExist(id);
        return this.cartRepository.get_products_by_id_client(id);
    }

    /**
     * Verify if cart exists by id
     * @param id Cart id
     * @throws CartNotFound If cart does not exist
     */
    public void cartExist(long id) throws CartNotFound {
        boolean exist = this.cartRepository.existsById(id);
        if(!exist) throw new CartNotFound();
    }

    /**
     * Delete cart from id
     * @param id Cart id
     * @throws CartNotFound If cart does not exist
     */
    public void delete(long id) throws CartNotFound {
        this.cartExist(id);
        this.cartRepository.deleteById(id);
    }

    /**
     * Reduce cart stock
     * @param cart Cart whose stock will be reduced
     * @return String with new stocks
     * @throws ProductNotFound If product does not exist
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
     * Make cart purchase
     * @param cart Cart to be purchased
     * @throws ClientNotFound If client does not exist
     * @throws ProductNotFound If product does not exist
     * @throws insufficientStock If product stock is insufficient
     * @throws SizeNotFound If product size does not exist
     * @throws CartNotFound If cart does not exist
     * @throws UnauthorizedPurchase If purchase is unauthorized
     */
    public void buyCart(Cart cart) throws ClientNotFound, ProductNotFound, insufficientStock, SizeNotFound, CartNotFound, UnauthorizedPurchase {

        cart = this.cartRepository.getReferenceById(cart.getId());

        if (!cart.isAvailable()) {
            throw new UnauthorizedPurchase();
        }

        long clientId = cart.getClient().getId();
        long productId = cart.getProduct().getId();

        this.clientService.clientExist(clientId);
        this.productService.productExist(productId);
        this.cartExist(cart.getId());

        this.verifyStock(cart, 0);

        cart.getProduct().setStock(this.reduceStock(cart));

        String size = cart.getProduct().getSize();
        int stock = Integer.parseInt(this.calculateStockBySize(size, cart.getSize(), cart.getProduct().getStock()));

        this.cartRepository.verify_purchase_available(productId, cart.getSize(), stock);
        this.purchaseService.savePurchase(cart);
        this.cartRepository.delete(cart);
    }

    /**
     * Make purchase for all carts
     * @param id Cliente id
     * @throws ClientNotFound If client does not exist
     * @throws ProductNotFound If product does not exist
     * @throws insufficientStock If product stock is insufficient
     * @throws SizeNotFound If product size does not exist
     * @throws CartNotFound If cart does not exist
     * @throws UnauthorizedPurchase If purchase is unauthorized
     */
    public void buyAllCart(long id) throws ClientNotFound, ProductNotFound, insufficientStock, SizeNotFound, CartNotFound, UnauthorizedPurchase {
        List<Cart> carts = this.importList(id);
        if(carts.isEmpty()) throw new CartNotFound();
        for (Cart cart: carts) {
            this.buyCart(cart);
        }
    }
}
