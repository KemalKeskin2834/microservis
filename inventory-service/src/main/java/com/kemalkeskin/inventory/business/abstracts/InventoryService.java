package com.kemalkeskin.inventory.business.abstracts;

import com.kemalkeskin.inventory.business.Dtos.CreateInventoryRequest;
import com.kemalkeskin.inventory.model.Inventory;

public interface InventoryService {


    boolean isInStock(int productId,int quantity);

    void createInventory(int productId, int quantity);

    void updateInventory(int productId, int quantity);
}
