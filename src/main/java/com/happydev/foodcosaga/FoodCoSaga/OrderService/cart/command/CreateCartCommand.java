package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command;

import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
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
public class CreateCartCommand {
    @TargetAggregateIdentifier
    private String cartId;
    private String customerId;
    private Float subTotal;
    private String status;
    private List<String> orderItems;
    private String createdAt;
}
