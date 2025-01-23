package com.happydev.foodcosaga.FoodCoSaga.CommonService.commands;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelPaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus = Constants.STATUS_CANCELLED;
}
