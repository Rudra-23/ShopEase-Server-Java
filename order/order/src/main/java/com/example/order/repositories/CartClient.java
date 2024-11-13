package com.example.order.repositories;

import com.example.order.models.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/carts/cart/{cartId}")
    CartDTO getCart(@PathVariable("cartId") Long cartId);

    @DeleteMapping("/carts/cart/{cartId}")
    public void deleteCart(@PathVariable Long cartId);
}

