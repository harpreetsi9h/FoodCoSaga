package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Delivery {

    @Id
    private String deliveryId;
    private String orderId;
    private String deliveryDriverId;
    private String deliveryStatus;
}
