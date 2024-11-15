package com.kemalkeskin.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory",url = "${inventory.url}")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET,value = ("/inventories"))
    public boolean isInStock(@RequestParam int productId, @RequestParam int quantity);

    @RequestMapping(method = RequestMethod.PUT,value = ("/inventories"))
    public double updateInventory(@RequestParam int productId,@RequestParam int quantity);
}
