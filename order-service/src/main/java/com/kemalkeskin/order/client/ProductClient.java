package com.kemalkeskin.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "products",url = "http://localhost:8080")
public interface ProductClient {


    @RequestMapping(method = RequestMethod.GET,value = ("/products/price/{id}"))
    double giveMePrice(@PathVariable int id);

}
