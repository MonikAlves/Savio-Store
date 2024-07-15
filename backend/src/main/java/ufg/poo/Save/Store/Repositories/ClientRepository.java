package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    Client getReferenceByEmail(String email);
}
