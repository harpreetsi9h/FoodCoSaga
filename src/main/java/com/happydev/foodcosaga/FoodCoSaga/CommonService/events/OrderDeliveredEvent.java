package com.happydev.foodcosaga.FoodCoSaga.CommonService.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDeliveredEvent {
    private String deliveryId;
    private String orderId;
    private String deliveryDriverId;
    private String deliveryStatus;
}
