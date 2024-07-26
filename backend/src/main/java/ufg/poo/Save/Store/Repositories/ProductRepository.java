package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufg.poo.Save.Store.Entities.Product;

import java.util.List;

/**
 * Product Repository
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    /**
     * Get product by id
     *
     * @param id Product id
     * @return Product
     */
    Product getProductById(Long id);
}
