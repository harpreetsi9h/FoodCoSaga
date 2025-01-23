package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderItemsByIds {
    private List<String> orderItemIds;
}
