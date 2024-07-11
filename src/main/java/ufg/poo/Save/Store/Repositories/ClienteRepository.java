package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Cliente;
import ufg.poo.Save.Store.Entities.Produtos;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Procedure(name = "get_products_by_ID")
    List<Produtos> get_products_by_ID(@Param("id") long id);
}
