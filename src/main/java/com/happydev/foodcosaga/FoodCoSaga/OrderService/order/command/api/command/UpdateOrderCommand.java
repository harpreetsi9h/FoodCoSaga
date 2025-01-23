package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateOrderCommand {
    @TargetAggregateIdentifier
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
