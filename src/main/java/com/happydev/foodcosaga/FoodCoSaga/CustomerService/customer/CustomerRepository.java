package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
