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
     * @param id Client whose purchases will be got
     * @return List with all client purchases
     */
    @GetMapping("/account/{id}")
    public ResponseEntity<?> getPurchases(@PathVariable long id) {
        List<Purchase> purchases;

        try {
            purchases = this.purchaseService.importPurchases(id);
        }
        catch (SuperException e) {
            return ResponseDTO.response(e);
        }

        return ResponseEntity.ok().body(purchases);
    }
}