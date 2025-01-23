package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event;

import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import lombok.Data;

import java.util.List;

@Data
public class CartCreatedEvent {

    private String cartId;
    private String customerId;
    private Float subTotal;
    private String status;
    private List<String> orderItems;
    private String createdAt;
}
