package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Procedure(name = "get_products_by_ID")
    List<Product> get_products_by_ID(@Param("id") long id);
}
