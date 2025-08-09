package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.query.api;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.InternalServices;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail.CardDetail;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.Customer;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.CustomerRepository;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.CustomerResponseModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerProjection {

    private final CustomerRepository repository;
    private final InternalServices internalServices;

    @Autowired
    public CustomerProjection(CustomerRepository repository, InternalServices internalServices) {
        this.repository = repository;
        this.internalServices = internalServices;
    }

    @QueryHandler
    public List<CustomerResponseModel> handle(GetCustomersQuery query) {
        List<Customer> dataList = repository.findAll();

        return dataList.stream().map(
                data -> CustomerResponseModel.builder()
                        .customerId(data.getCustomerId())
                        .createdAt(data.getCreatedAt())
                        .pic(data.getPic())
                        .email(data.getEmail())
                        .firstName(data.getFirstName())
                        .lastName(data.getLastName())
                        .phone(data.getPhone())
                        .address(internalServices.getAddress(data.getAddressId()))
                        .cardDetail(!data.getCardDetailId().isEmpty() ? internalServices.getCardDetail(data.getCardDetailId()) : new CardDetail())
                        .build()
        ).collect(Collectors.toList());
    }

    @QueryHandler
    public CustomerResponseModel handle(GetCustomerByIdQuery query) {
        if(repository.findById(query.getCustomerId()).isEmpty())
            return null;
        Customer customer = repository.findById(query.getCustomerId()).get();
        return CustomerResponseModel.builder()
                .customerId(customer.getCustomerId())
                .createdAt(customer.getCreatedAt())
                .pic(customer.getPic())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .address(internalServices.getAddress(customer.getAddressId()))
                .cardDetail(!customer.getCardDetailId().isEmpty() ? internalServices.getCardDetail(customer.getCardDetailId()) : new CardDetail())
                .build();
    }
}
