package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    /**
     * Get purchase by id client
     *
     * @param ids Client id
     */
    @Procedure(name = "get_purchase_by_id_client")
    List<Purchase> get_purchase_by_id_client(@Param("ids") long ids);
}
