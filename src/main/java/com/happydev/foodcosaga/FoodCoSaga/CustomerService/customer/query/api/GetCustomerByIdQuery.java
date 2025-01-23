package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.query.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerByIdQuery {

    private String customerId;
}
