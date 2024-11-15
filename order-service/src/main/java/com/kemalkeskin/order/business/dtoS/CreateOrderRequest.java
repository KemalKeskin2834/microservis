package com.kemalkeskin.order.business.dtoS;

public record CreateOrderRequest(String orderNumber,int productId,double price,int quantity) {

}
