package com.kemalkeskin.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory",url = "http://localhost:8083")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.POST,value = ("/inventories"))
    void createInventory(@RequestParam int productId, @RequestParam int quantity);
}
