package com.example.cart.services;

import com.example.cart.models.Cart;
import com.example.cart.models.CartItem;
import com.example.cart.models.ProductDTO;
import com.example.cart.repositories.CartRepository;
import com.example.cart.repositories.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductClient productClient;

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCart(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public Cart addItemToCart(Long cartId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ProductDTO product = productClient.getProductById(cartItem.getProductId());

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + cartItem.getQuantity();
            if (product.getQuantity() < newQuantity) {
                throw new RuntimeException("Requested quantity not available in stock");
            }

            item.setQuantity(newQuantity);
        } else {
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Requested quantity not available in stock");
            }

            cart.getItems().add(cartItem);
        }

        return cartRepository.save(cart);
    }

    public void deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
