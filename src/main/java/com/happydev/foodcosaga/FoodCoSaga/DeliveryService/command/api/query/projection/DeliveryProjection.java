package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.query.projection;

import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.data.Delivery;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.data.DeliveryRepository;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.query.queries.GetDeliveries;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DeliveryProjection {

    private DeliveryRepository repository;

    public DeliveryProjection(DeliveryRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<Delivery> handle(GetDeliveries query) {
        return repository.findAll();
    }
}
