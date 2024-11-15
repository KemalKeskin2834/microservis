package com.kemalkeskin.inventory.business.Dtos;

public class CreateInventoryRequest {
    private int quantity;

    public CreateInventoryRequest(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
