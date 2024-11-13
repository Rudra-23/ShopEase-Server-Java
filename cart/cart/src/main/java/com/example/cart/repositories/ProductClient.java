package com.example.cart.repositories;

import com.example.cart.models.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/product/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}
