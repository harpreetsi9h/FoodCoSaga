package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.CartCloseEvent;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.Cart;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartEventsHandler {

    private CartRepository repository;

    public CartEventsHandler(CartRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(CartCreatedEvent event) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(event, cart);
        repository.save(cart);
        log.info("CartCreatedEvent - Cart Created with Id= {}",event.getCartId());
    }

    @EventHandler
    public void on(UpdateCartEvent event) {
        if(repository.findById(event.getCartId()).isEmpty()) {
            log.error("UpdateCartEvent - {} {}", Constants.CART_NOT_FOUND_WITH_ID, event.getCartId());
            return;
        }
        Cart cart = new Cart();
        BeanUtils.copyProperties(event, cart);
        repository.save(cart);
        log.info("UpdateCartEvent - Cart Updated with Id= {}",event.getCartId());
    }

    @EventHandler
    public void on(CartCloseEvent event) {
        if(repository.findById(event.getCartId()).isEmpty()) {
            log.error("CartCloseEvent - {} {}", Constants.CART_NOT_FOUND_WITH_ID, event.getCartId());
            return;
        }
        Cart cart = repository.findById(event.getCartId()).get();
        cart.setStatus(event.getStatus());
        repository.save(cart);
        log.info("CartCloseEvent - Cart Status Updated with Id= {}",event.getCartId());
    }
}
