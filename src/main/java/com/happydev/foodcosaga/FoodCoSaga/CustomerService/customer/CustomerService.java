package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.InternalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final InternalServices internalServices;

    @Autowired
    public CustomerService(CustomerRepository repository, InternalServices internalServices) {
        this.repository = repository;
        this.internalServices = internalServices;
    }

    public List<CustomerResponseModel> getCustomers() {
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
                        .cardDetail(internalServices.getCardDetail(data.getCardDetailId()))
                        .build()
        ).collect(Collectors.toList());
    }

    public String createCustomer(Customer customer) {
        customer.setCustomerId(UUID.randomUUID().toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        customer.setCreatedAt(timestamp.toString());
        repository.save(customer);
        return customer.getCustomerId();
    }

    public String removeCustomer(String customerId) throws CustomMessageException {
        if (repository.findById(customerId).isPresent()) {
            repository.deleteById(customerId);
            return Constants.CUSTOMER_REMOVED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.CUSTOMER_NOT_FOUND_WITH_ID+customerId);

    }

    public CustomerResponseModel getCustomer(String customerId) throws CustomMessageException {
        if(repository.findById(customerId).isEmpty())
            throw new CustomMessageException(Constants.CUSTOMER_NOT_FOUND_WITH_ID+customerId);
        Customer customer = repository.findById(customerId).get();
        return CustomerResponseModel.builder()
                .customerId(customer.getCustomerId())
                .createdAt(customer.getCreatedAt())
                .pic(customer.getPic())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .address(internalServices.getAddress(customer.getAddressId()))
                .cardDetail(internalServices.getCardDetail(customer.getCardDetailId()))
                .build();
    }

    public String updateCustomer(Customer customer) throws CustomMessageException {

        if (repository.findById(customer.getCustomerId()).isPresent()) {
            repository.save(customer);
            return Constants.CUSTOMER_UPDATED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.CUSTOMER_NOT_FOUND_WITH_ID+customer.getCustomerId());

    }
}
