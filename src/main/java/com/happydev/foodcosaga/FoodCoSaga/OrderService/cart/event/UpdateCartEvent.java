package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCartEvent {

    private String cartId;
    private String customerId;
    private Float subTotal;
    private String status;
    private List<String> orderItems;
    private String createdAt;
}
