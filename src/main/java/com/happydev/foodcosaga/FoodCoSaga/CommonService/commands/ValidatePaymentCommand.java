package com.happydev.foodcosaga.FoodCoSaga.CommonService.commands;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail.CardDetail;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ValidatePaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private String cartId;
    private CardDetail cardDetails;
}
