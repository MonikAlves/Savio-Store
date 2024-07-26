package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    /**
     * Get all product of client by client id
     *
     * @param ids Client id
     */
    @Procedure(name = "get_products_by_id_client")
    List<Cart> get_products_by_id_client(@Param("ids") long ids);

    /**
     * Verify availability of the product in carts
     *
     * @param id_p Product id
     * @param size Size
     * @param stock Stock
     */
    @Procedure(name = "verify_purchase_available")
    void verify_purchase_available(@Param("id_p") long id_p, @Param("size") String size, @Param("stock") int stock);

    /**
     * Calculate total int line
     *
     * @param id_c Client id
     * @param id_p Product id
     * @param size Size
     */
    @Procedure(name = "calculate_total_line")
    void calculate_total_line(@Param("id_c") long id_c,@Param("id_p") long id_p,@Param("size") String size);

    /**
     * Get cart by client id and product id
     *
     * @param id_c Client id
     * @param id_p Product id
     * @param size Size
     */
    @Procedure(name = "get_cart_by_id_client_and_id_product")
    Cart get_cart_by_id_client_and_id_product(@Param("id_c") long id_c,@Param("id_p") long id_p,@Param("size") String size);

    /**
     * Verify if cart exist
     *
     * @param client Client
     * @param product Product
     * @param size Size
     */
    @Procedure(name = "verify_cart_exist")
    boolean verify_cart_exist(@Param("client") long client,@Param("product") long product,@Param("size") String size);

    /**
     * Verify stock cart
     *
     * @param ids Ids
     * @param quantity Quantity
     * @param stock Stock
     */
    @Procedure(name = "verify_stock_cart")
    Boolean verify_stock_cart(@Param("ids") long ids,@Param("quantity") int quantity ,@Param("stock") int stock);
}
