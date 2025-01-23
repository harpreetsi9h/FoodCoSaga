package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
