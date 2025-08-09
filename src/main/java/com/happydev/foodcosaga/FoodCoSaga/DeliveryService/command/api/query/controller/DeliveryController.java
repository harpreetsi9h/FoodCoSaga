package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.query.controller;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.data.Delivery;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.query.queries.GetDeliveries;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class DeliveryController {

    private final QueryGateway queryGateway;

    public DeliveryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @CrossOrigin
    @GetMapping(Constants.URL_DELIVERY)
    public ResponseEntity<List<Delivery>> getDeliveries() {
        GetDeliveries query = new GetDeliveries();

        List<Delivery> deliveries = queryGateway.query(query, ResponseTypes.multipleInstancesOf(Delivery.class)).join();

        return ResponseEntity.ok(deliveries);
    }
}
