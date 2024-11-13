package com.example.order.models.dto;
import lombok.Data;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private List<CartItemDTO> items;

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
