package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Product;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Procedure(name = "calculate_total_line")
    void calculate_total_line(@Param("id_c") long id_c,@Param("id_p") long id_p);

    @Procedure(name = "verify_cart_exist")
    String verify_cart_exist(@Param("client") long client,@Param("product") long product);
}
