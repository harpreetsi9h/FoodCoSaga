package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCartByCustomer {
    private String customerId;
}
