package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateOrderItemCommand {

    @TargetAggregateIdentifier
    private String orderItemId;
    private Integer quantity;
}
