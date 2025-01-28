package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.events;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ProcessingGroup("orderItem")
public class OrderItemEventHandler {

    private final OrderItemRepository repository;

    public OrderItemEventHandler(OrderItemRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(OrderItemCreatedEvent event) {
        OrderItem orderItem = new OrderItem();

        BeanUtils.copyProperties(event, orderItem);

        repository.save(orderItem);
    }

    @EventHandler
    public void on(OrderItemUpdateEvent event) {
        if(repository.findById(event.getOrderItemId()).isEmpty()) {
            log.error("OrderItemUpdateEvent - {} {}", Constants.ORDER_ITEM_NOT_FOUND_WITH_ID, event.getOrderItemId());
            return;
        }

        OrderItem orderItem = repository.findById(event.getOrderItemId()).get();
        orderItem.setQuantity(event.getQuantity());
        repository.save(orderItem);
    }
}
