package com.happydev.foodcosaga.FoodCoSaga.CommonService.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeliverOrderCommand {

    @TargetAggregateIdentifier
    private String deliveryId;
    private String deliveryDriverId;
    private String orderId;
}
