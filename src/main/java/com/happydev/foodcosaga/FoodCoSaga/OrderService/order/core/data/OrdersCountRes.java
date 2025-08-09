package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCountRes {
    private long count;
    private String status;
}
