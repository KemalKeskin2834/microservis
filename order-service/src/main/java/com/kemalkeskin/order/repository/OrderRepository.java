package com.kemalkeskin.order.repository;

import com.kemalkeskin.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> {
    Order findByOrderNumber(String orderNumber);
}
