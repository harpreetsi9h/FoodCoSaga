package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.events;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.OrderDeliveredEvent;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.data.Delivery;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.data.DeliveryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventHandler {

    private DeliveryRepository deliveryRepository;

    public DeliveryEventHandler(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @EventHandler
    public void on(OrderDeliveredEvent event) {
        Delivery delivery
                = new Delivery();
        BeanUtils.copyProperties(event, delivery);
        deliveryRepository.save(delivery);
    }
}
