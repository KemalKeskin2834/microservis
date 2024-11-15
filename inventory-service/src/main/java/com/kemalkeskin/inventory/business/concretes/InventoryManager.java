package com.kemalkeskin.inventory.business.concretes;

import com.kemalkeskin.inventory.business.Dtos.CreateInventoryRequest;
import com.kemalkeskin.inventory.business.abstracts.InventoryService;
import com.kemalkeskin.inventory.model.Inventory;
import com.kemalkeskin.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryManager implements InventoryService {

    private InventoryRepository inventoryRepository;


    @Autowired
    public InventoryManager(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public boolean isInStock(int productId,int quantity) {
        return inventoryRepository.existsByProductIdAndQuantityIsGreaterThanEqual(productId, quantity);
    }

    @Override
    public void createInventory(int productId, int quantity) {
        Inventory inventory=new Inventory(productId,quantity);
       this.inventoryRepository.save(inventory);
    }

    @Override
    public void updateInventory(int productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (inventory.getQuantity() < quantity) {
            throw new IllegalStateException("Not enough stock");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);


    }
}
