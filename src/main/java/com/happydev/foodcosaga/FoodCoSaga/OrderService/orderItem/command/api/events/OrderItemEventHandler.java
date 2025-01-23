package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.events;

import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItemRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("orderItem")
public class OrderItemEventHandler {

    private final OrderItemRepository repository;

    public OrderItemEventHandler(OrderItemRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(OrderItemCreatedEvent event) {
        OrderItem restModel = new OrderItem();

        BeanUtils.copyProperties(event, restModel);

        repository.save(restModel);
    }
}
