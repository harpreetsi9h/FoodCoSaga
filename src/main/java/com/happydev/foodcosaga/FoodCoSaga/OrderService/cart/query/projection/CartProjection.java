package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.projection;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.Cart;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.CartRepository;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartByCustomer;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CartProjection {

    private CartRepository repository;

    public CartProjection(CartRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public Cart handle(GetCartByCustomer query) {
        List<Cart> carts = repository.findAll().stream()
                .filter(cart -> cart.getCustomerId().equals(query.getCustomerId()) &&
                        cart.getStatus().equals(Constants.STATUS_OPEN)).toList();
        if(carts.isEmpty()) {
            log.error("UpdateCartEvent - Cart not found with customer ID= {}", query.getCustomerId());
            return null;
        }
        return carts.get(0);
    }

    @QueryHandler
    public Cart handle(GetCartByIdQuery query) {
        if(repository.findById(query.getCartId()).isEmpty()) {
            log.error("GetCartByIdQuery - {} {}", Constants.CART_NOT_FOUND_WITH_ID, query.getCartId());
            return null;
        }
        return repository.findById(query.getCartId()).get();
    }
}
