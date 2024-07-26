package ufg.poo.Save.Store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufg.poo.Save.Store.Entities.Client;

import java.util.Optional;

/**
 * Client Repository
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Find client by email
     *
     * @param email Client email
     * @return Optional to client
     */
    Optional<Client> findByEmail(String email);

    /**
     * Get reference to client by email
     *
     * @param email Client email
     * @return Client
     */
    Client getReferenceByEmail(String email);
}
