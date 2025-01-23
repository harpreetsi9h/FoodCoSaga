package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core;

import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartModel {

    private String cartId;
    @NotBlank
    private String customerId;
    private Float subTotal;
    private String status;
    private List<OrderItemModel> orderItems;
    private String createdAt;
}
