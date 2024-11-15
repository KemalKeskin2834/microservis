package com.kemalkeskin.order.business.concretes;

import com.kemalkeskin.order.business.dtoS.OrderRequest;
import com.kemalkeskin.order.client.InventoryClient;
import com.kemalkeskin.order.business.abstracts.OrderService;
import com.kemalkeskin.order.business.dtoS.CreateOrderRequest;
import com.kemalkeskin.order.business.dtoS.OrderResponse;
import com.kemalkeskin.order.client.ProductClient;
import com.kemalkeskin.order.core.exception.BusinessException;
import com.kemalkeskin.order.model.Order;
import com.kemalkeskin.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;
    private InventoryClient inventoryClient;
    private ProductClient productClient;


    private static final String TOPIC = "order-created";

    @Autowired
    public OrderManager(OrderRepository orderRepository,InventoryClient inventoryClient,ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient=inventoryClient;
        this.productClient=productClient;

    }

    @Override
    public List<OrderResponse> listOrders() {
        return orderRepository.findAll().stream().map(order -> new OrderResponse(order.getId(),order.getOrderNumber(),order.getProductId(),order.getPrice(),order.getQuantity())).toList();

    }

    @Override
    public OrderResponse  getByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        return  new OrderResponse(order.getId(),order.getOrderNumber(),order.getProductId(),order.getPrice(),order.getQuantity());

    }


    @Override
    public void placeOrder(OrderRequest orderRequest) {

        var isInventoryClient=inventoryClient.isInStock(
               orderRequest.getProductId(),orderRequest.getQuantity());
        if(isInventoryClient){
            double totalPrice=productClient.giveMePrice(orderRequest.getProductId());
            double price=(totalPrice*orderRequest.getQuantity());

            Order order=new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setProductId(orderRequest.getProductId());
            order.setQuantity(orderRequest.getQuantity());
            order.setPrice(price);

            orderRepository.save(order);
            inventoryClient.updateInventory(order.getProductId(), order.getQuantity());

        }
        else {
            throw  new BusinessException("Product with skuCode: "+orderRequest.getProductId()+" is not in stock");
        }
    }




    @Override
    public void deleteById(int id) {

    }
}
