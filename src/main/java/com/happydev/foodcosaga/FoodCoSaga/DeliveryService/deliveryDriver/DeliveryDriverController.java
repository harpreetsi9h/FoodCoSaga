package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.CountResModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class DeliveryDriverController {

    private final DeliveryDriverService service;

    @Autowired
    public DeliveryDriverController(DeliveryDriverService service) {
        this.service = service;
    }

//    @GetMapping(Constants.URL_DELIVERY_DRIVER)
//    public ResponseEntity<List<DeliveryDriver>> getDeliveryDrivers() {
//        return ResponseEntity.ok(service.getDeliveryDrivers());
//    }

    @CrossOrigin
    @PostMapping(Constants.URL_DELIVERY_DRIVER)
    public ResponseEntity<String> createDeliveryDriver(@RequestBody @Valid DeliveryDriver driver) {
        String driverId = service.createDeliveryDriver(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(driverId);
    }

    @CrossOrigin
    @GetMapping(Constants.URL_DELIVERY_DRIVER+"/{driverId}")
    public ResponseEntity<DeliveryDriver> getDeliveryDriver(@PathVariable String driverId) throws CustomMessageException {
        return ResponseEntity.ok(service.getDeliveryDriver(driverId));
    }

    @CrossOrigin
    @DeleteMapping(Constants.URL_DELIVERY_DRIVER+"/{driverId}")
    public ResponseEntity<String> removeDeliveryDriver(@PathVariable String driverId) throws CustomMessageException {
        return ResponseEntity.ok(service.removeDeliveryDriver(driverId));
    }

    @CrossOrigin
    @PutMapping(Constants.URL_DELIVERY_DRIVER)
    public ResponseEntity<String> updateDeliveryDriver(@RequestBody @Valid DeliveryDriver driver) throws CustomMessageException {
        return ResponseEntity.ok(service.updateDeliveryDriver(driver));
    }

    @CrossOrigin
    @GetMapping(Constants.URL_DELIVERY_DRIVER+"/count")
    public ResponseEntity<CountResModel> getCustomerCount() {
        return ResponseEntity.ok(service.getDeliveryDriversCount());
    }
}
