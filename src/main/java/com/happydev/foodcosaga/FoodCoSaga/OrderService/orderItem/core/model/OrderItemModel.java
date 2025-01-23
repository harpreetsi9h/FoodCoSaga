package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModel {

    private String orderItemId;
    @NotBlank
    private String menuItemId;
    private Integer quantity;
    private String createdAt;
}
