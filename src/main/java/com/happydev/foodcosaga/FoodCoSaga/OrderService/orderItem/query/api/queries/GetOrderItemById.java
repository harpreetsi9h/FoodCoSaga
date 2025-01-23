package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderItemById {

    private String orderItemId;
}
