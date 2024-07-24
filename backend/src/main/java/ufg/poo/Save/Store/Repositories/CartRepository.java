package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Procedure(name = "calculate_total_line")
    void calculate_total_line(@Param("id_c") long id_c,@Param("id_p") long id_p,@Param("size") String size);

    @Procedure(name = "get_cart_by_id_client_and_id_product")
    Cart get_cart_by_id_client_and_id_product(@Param("id_c") long id_c,@Param("id_p") long id_p,@Param("size") String size);

    @Procedure(name = "verify_cart_exist")
    boolean verify_cart_exist(@Param("client") long client,@Param("product") long product,@Param("size") String size);

    @Procedure(name = "verify_stock_cart")
    Boolean verify_stock_cart(@Param("ids") long ids,@Param("quantity") int quantity ,@Param("stock") int stock);
}
