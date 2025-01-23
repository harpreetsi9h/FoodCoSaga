package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.events;

import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private Float subTotal;
    private String cartId;
    private Float serviceFee;
    private Float deliveryFee;
    private Float totalAmount;
    private String orderStatus;
    private String createdAt;
}
