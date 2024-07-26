package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Cart;
import ufg.poo.Save.Store.Entities.Purchase;
import ufg.poo.Save.Store.Exception.ClientNotFound;
import ufg.poo.Save.Store.Repositories.PurchaseRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Purchase Service
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ClientService clientService;

    /**
     * Create purchase from cart and add it to purchase repository
     * @param cart Cart whose purchase will be created
     */
    public void savePurchase(Cart cart) {
        Purchase purchase = new Purchase();

        purchase.setClient(cart.getClient());
        purchase.setProduct(cart.getProduct());
        purchase.setQuantity(cart.getQuantity());
        purchase.setSize(cart.getSize());
        purchase.setTotal(cart.getTotal());
        purchase.setDate(LocalDate.now());

        this.purchaseRepository.save(purchase);
    }

    /**
     * Get a list with all client purchases
     * @param id Client id
     * @return List with all client purchases
     * @throws ClientNotFound If client was not found
     */
    public List<Purchase> importPurchases(long id) throws ClientNotFound {
        this.clientService.clientExist(id);
        return this.purchaseRepository.get_purchase_by_id_client(id);
    }
}
