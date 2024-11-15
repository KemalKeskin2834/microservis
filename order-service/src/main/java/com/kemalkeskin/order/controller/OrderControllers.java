package com.kemalkeskin.order.controller;

import com.kemalkeskin.order.business.abstracts.OrderService;
import com.kemalkeskin.order.business.dtoS.CreateOrderRequest;
import com.kemalkeskin.order.business.dtoS.OrderRequest;
import com.kemalkeskin.order.business.dtoS.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderControllers {

    private OrderService orderService;

    @Autowired
    public OrderControllers(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> listOrders(){
        return orderService.listOrders();
    }

    @GetMapping("/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse  getByOrderNumber(@PathVariable String orderNumber){
        return orderService.getByOrderNumber(orderNumber);
    }

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody OrderRequest orderRequest){
     return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
