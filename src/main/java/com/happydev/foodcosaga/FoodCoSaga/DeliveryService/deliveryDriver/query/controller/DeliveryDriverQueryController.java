package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.query.controller;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.DeliveryDriver;
import com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver.query.queries.GetDeliveryDrivers;
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
public class DeliveryDriverQueryController {

    private final QueryGateway queryGateway;

    public DeliveryDriverQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(Constants.URL_DELIVERY_DRIVER)
    public ResponseEntity<List<DeliveryDriver>> getDeliveryDrivers() {
        GetDeliveryDrivers query = new GetDeliveryDrivers();

        List<DeliveryDriver> dd = queryGateway.query(query, ResponseTypes.multipleInstancesOf(DeliveryDriver.class)).join();

        return ResponseEntity.ok(dd);
    }
}
