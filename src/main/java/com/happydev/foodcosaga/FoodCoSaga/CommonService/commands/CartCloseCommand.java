package com.happydev.foodcosaga.FoodCoSaga.CommonService.commands;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartCloseCommand {
    @TargetAggregateIdentifier
    private String cartId;
    private String status = Constants.STATUS_CLOSED;
}
