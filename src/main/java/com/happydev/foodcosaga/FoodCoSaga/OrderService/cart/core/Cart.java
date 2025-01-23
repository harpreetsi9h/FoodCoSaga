package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core;

import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String cartId;
    @NotBlank
    private String customerId;
    private Float subTotal;
    private String status;
    private List<String> orderItems;
    private String createdAt;
}
