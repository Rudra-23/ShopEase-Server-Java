package com.example.cart.controllers;

import com.example.cart.models.Cart;
import com.example.cart.models.CartItem;
import com.example.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/create")
    public ResponseEntity<Cart> createCart() {
        Cart cart = new Cart();

        cart = cartService.addCart(cart);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/cart/{cartId}/add-item")
    public ResponseEntity<Cart> addItemToCart(
            @PathVariable Long cartId,
            @RequestBody CartItem cartItem) {
        Cart updatedCart = cartService.addItemToCart(cartId, cartItem);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
    }
}
