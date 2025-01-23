package com.happydev.foodcosaga.FoodCoSaga.CommonService.commands;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String orderStatus = Constants.STATUS_CANCELLED;
}
