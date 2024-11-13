package com.example.order.services;

import com.example.order.models.dto.AddressDTO;
import com.example.order.models.dto.CartDTO;
import com.example.order.models.dto.CartItemDTO;
import com.example.order.models.Order;
import com.example.order.repositories.AddressClient;
import com.example.order.repositories.CartClient;
import com.example.order.repositories.OrderRepository;
import com.example.order.repositories.ProductClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private CartClient cartClient;
    private AddressClient addressClient;
    private OrderRepository orderRepository;
    private ProductClient productClient;

    public OrderService(CartClient cartClient, AddressClient addressClient, OrderRepository orderRepository, ProductClient productClient) {
        this.cartClient = cartClient;
        this.addressClient = addressClient;
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public Order createOrder(Long cartId, Long addressId) {
        CartDTO cartDTO = cartClient.getCart(cartId);
        AddressDTO address = addressClient.getAddressById(addressId);

        validateStock(cartDTO.getItems());

        double totalPrice = calculateTotalPrice(cartDTO.getItems());

        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setAddressId(address.getId());
        order.setAddressId(cartDTO.getId());

        orderRepository.save(order);

        updateProductQuantites(cartDTO.getItems());
        cartClient.deleteCart(cartId);

        return order;
    }

    private void updateProductQuantites(List<CartItemDTO> cartItems) {
        cartItems.forEach(item -> {
            int quantity = productClient.getProductById(item.getProductId()).getQuantity();
            productClient.updateStock(item.getProductId(), quantity - item.getQuantity());
        });
    }

    private void validateStock(List<CartItemDTO> cartItems) {
        cartItems.forEach(item -> {
            if (!isProductInStock(item.getProductId(), item.getQuantity())) {
                throw new RuntimeException("Product " + item.getProductId() + " is out of stock.");
            }
        });
    }

    private boolean isProductInStock(Long productId, int quantity) {
        return productClient.getProductById(productId).getQuantity() >= quantity;
    }

    private double calculateTotalPrice(List<CartItemDTO> cartItems) {
        double total = 0;
        for (CartItemDTO item : cartItems) {
            double price = productClient.getProductById(item.getProductId()).getPrice();
            total += price * item.getQuantity();
        }
        return total;
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
