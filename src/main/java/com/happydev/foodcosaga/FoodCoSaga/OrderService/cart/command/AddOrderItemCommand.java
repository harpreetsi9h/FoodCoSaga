package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderItemCommand {
    @TargetAggregateIdentifier
    private String cartId;
    private List<String> orderItems;
}
