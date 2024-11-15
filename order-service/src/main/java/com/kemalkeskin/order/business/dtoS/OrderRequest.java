package com.kemalkeskin.order.business.dtoS;

import java.util.UUID;

public class OrderRequest {


    private int productId;
    private int quantity;



    public int getProductId() {
        return productId;
    }




    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
