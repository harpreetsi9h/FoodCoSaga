package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderItemCommand {

    @TargetAggregateIdentifier
    private String orderItemId;
    private String menuItemId;
    private Integer quantity;
    private String createdAt;
}
