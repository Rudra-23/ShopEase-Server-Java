package com.example.order.repositories;


import com.example.order.models.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/product/{id}")
    ProductDTO getProductById(@PathVariable Long id);

    @PutMapping("/products/product/{id}/update-stock")
    void updateStock(@PathVariable Long id, @RequestParam int quantity);
}

