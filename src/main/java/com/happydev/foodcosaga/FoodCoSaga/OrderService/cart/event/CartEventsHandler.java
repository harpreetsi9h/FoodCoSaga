package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.CartCloseEvent;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.Cart;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.CartRepository;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemsByIds;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.MenuItem;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries.GetItemByIDQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CartEventsHandler {

    private final CartRepository repository;
    private final QueryGateway queryGateway;

    public CartEventsHandler(CartRepository repository, QueryGateway queryGateway)
    {
        this.repository = repository;
        this.queryGateway = queryGateway;
    }

    @EventHandler
    public void on(CartCreatedEvent event) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(event, cart);
        repository.save(updateSubTotal(cart));
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
        repository.save(updateSubTotal(cart));
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

    @EventHandler
    public void on(AddOrderItemEvent event) {
        if(repository.findById(event.getCartId()).isEmpty()) {
            log.error("AddOrderItemEvent - {} {}", Constants.CART_NOT_FOUND_WITH_ID, event.getCartId());
            return;
        }
        Cart cart = repository.findById(event.getCartId()).get();
        cart.getOrderItems().addAll(event.getOrderItems());
        repository.save(updateSubTotal(cart));
    }


    private Cart updateSubTotal(Cart cart) {
        float subTotal = getOrderItems(cart.getOrderItems())
                .stream()
                .map(oi -> oi.getQuantity() * getMenuItem(oi.getMenuItemId()).getPrice())
                .reduce(0.0f, Float::sum);
        cart.setSubTotal(subTotal);

        return cart;
    }

    private List<OrderItemModel> getOrderItems(List<String> orderItems) {
        GetOrderItemsByIds query = new GetOrderItemsByIds(orderItems);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(OrderItemModel.class)).join();
    }

    private MenuItem getMenuItem(String menuItemId) {
        GetItemByIDQuery query = new GetItemByIDQuery(menuItemId);
        return queryGateway.query(query, ResponseTypes.instanceOf(MenuItem.class)).join();
    }

}
