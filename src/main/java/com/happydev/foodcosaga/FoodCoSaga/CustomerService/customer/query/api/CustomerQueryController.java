package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.query.api;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.CustomerResponseModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.URL_API)
public class CustomerQueryController {

    private final QueryGateway queryGateway;

    public CustomerQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(Constants.URL_CUSTOMER+"/{customerId}")
    public ResponseEntity<CustomerResponseModel> getCustomer(@PathVariable String customerId) throws CustomMessageException {
        GetCustomerByIdQuery query = new GetCustomerByIdQuery(customerId);

        CustomerResponseModel customer =
                queryGateway.query(query, ResponseTypes.instanceOf(CustomerResponseModel.class)).join();
        return ResponseEntity.ok(customer);
    }
}
