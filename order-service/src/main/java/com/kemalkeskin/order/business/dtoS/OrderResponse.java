package com.kemalkeskin.order.business.dtoS;

public record OrderResponse(int id,String orderNumber,int productId,double price,int quantity) {
}
