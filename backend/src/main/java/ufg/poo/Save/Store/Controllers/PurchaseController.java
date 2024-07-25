package ufg.poo.Save.Store.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufg.poo.Save.Store.DTOS.ResponseDTO;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Purchase;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Services.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/SavioStore/Purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    /**
     * Get a list with all client purchases
     * @param client Client whose purchases will be got
     * @return List with all client purchases
     */
    @PostMapping("/account")
    public ResponseEntity<?> getPurchases(@RequestBody Client client) {
        List<Purchase> purchases;

        try {
            purchases = this.purchaseService.importPurchases(client.getId());
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().body(purchases);
    }
}