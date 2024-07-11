package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.poo.Save.Store.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
