package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer;

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
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

//    @GetMapping(Constants.URL_CUSTOMER)
//    public ResponseEntity<List<CustomerResponseModel>> getCustomers() {
//        return ResponseEntity.ok(service.getCustomers());
//    }

    @CrossOrigin
    @PostMapping(Constants.URL_CUSTOMER)
    public ResponseEntity<String> createCustomer(@RequestBody @Valid Customer customer) {
        String customerId = service.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerId);
    }

//    @GetMapping(Constants.URL_CUSTOMER+"/{customerId}")
//    public ResponseEntity<CustomerResponseModel> getCustomer(@PathVariable String customerId) throws CustomMessageException {
//        return ResponseEntity.ok(service.getCustomer(customerId));
//    }

    @CrossOrigin
    @DeleteMapping(Constants.URL_CUSTOMER+"/{customerId}")
    public ResponseEntity<String> removeCustomer(@PathVariable String customerId) throws CustomMessageException {
        return ResponseEntity.ok(service.removeCustomer(customerId));
    }

    @CrossOrigin
    @PutMapping(Constants.URL_CUSTOMER)
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid Customer customer) throws CustomMessageException {
        return ResponseEntity.ok(service.updateCustomer(customer));
    }

    @CrossOrigin
    @GetMapping(Constants.URL_CUSTOMER+"/count")
    public ResponseEntity<CountResModel> getCustomerCount() {
        return ResponseEntity.ok(service.getCustomerCount());
    }
}
