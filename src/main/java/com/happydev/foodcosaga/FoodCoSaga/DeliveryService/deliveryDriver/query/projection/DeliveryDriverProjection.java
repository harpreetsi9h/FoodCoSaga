package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.query.projection;

import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.DeliveryDriver;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.DeliveryDriverRepository;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.query.queries.GetDeliveryDrivers;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DeliveryDriverProjection {

    private DeliveryDriverRepository repository;

    public DeliveryDriverProjection(DeliveryDriverRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<DeliveryDriver> handle(GetDeliveryDrivers query) {
        return repository.findAll();
    }

}
