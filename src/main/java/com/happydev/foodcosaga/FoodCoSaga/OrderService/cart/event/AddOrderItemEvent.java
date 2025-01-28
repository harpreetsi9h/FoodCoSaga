package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderItemEvent {
    private String cartId;
    private List<String> orderItems;
}
