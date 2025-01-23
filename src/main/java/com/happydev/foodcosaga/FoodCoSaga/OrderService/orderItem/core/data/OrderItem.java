package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    private String orderItemId;
    private String menuItemId;
    private Integer quantity;
    private String createdAt;
}
