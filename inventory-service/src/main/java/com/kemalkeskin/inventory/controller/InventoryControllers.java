package com.kemalkeskin.inventory.controller;

import com.kemalkeskin.inventory.business.Dtos.CreateInventoryRequest;
import com.kemalkeskin.inventory.business.abstracts.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories")
public class InventoryControllers {

    private InventoryService inventoryService;

    public InventoryControllers(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam  int productId,@RequestParam int quantity){
        return inventoryService.isInStock(productId, quantity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestParam int productId,@RequestParam int quantity){
        this.inventoryService.createInventory(productId,quantity);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestParam int productId,@RequestParam int quantity) {
        this.inventoryService.updateInventory(productId,quantity);

    }
}

//spring boota openfeign senkron ieltişim sağlar
//yani order ile inventory arasında senkron bağlantı kurup
//order inventory cevap bekler cevap verdikten sonra devam eder
