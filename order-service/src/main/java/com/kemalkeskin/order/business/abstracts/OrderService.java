package com.kemalkeskin.order.business.abstracts;

import com.kemalkeskin.order.business.dtoS.CreateOrderRequest;
import com.kemalkeskin.order.business.dtoS.OrderRequest;
import com.kemalkeskin.order.business.dtoS.OrderResponse;
import com.kemalkeskin.order.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderResponse>listOrders();

    OrderResponse getByOrderNumber(String orderNumber);

    void placeOrder(OrderRequest orderRequest);

    void deleteById(int id);

}
