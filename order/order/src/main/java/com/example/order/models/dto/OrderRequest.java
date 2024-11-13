package com.example.order.models.dto;

public class OrderRequest {
    private Long cartId;
    private Long addressId;

    public OrderRequest() {}

    public OrderRequest(Long cartId, Long addressId) {
        this.cartId = cartId;
        this.addressId = addressId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
