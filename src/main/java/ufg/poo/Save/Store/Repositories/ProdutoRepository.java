package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufg.poo.Save.Store.Entities.Produtos;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos,String> {
    @Procedure(name = "get_products_by_ID")
    List<Produtos> get_products_by_ID(@Param("id") String id);
}
