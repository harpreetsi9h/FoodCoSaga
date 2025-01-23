package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.events;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.OrderCancelledEvent;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.OrderCompletedEvent;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.Orders;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventsHandler {

    private OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Orders order = new Orders();
        BeanUtils.copyProperties(event,order);
        orderRepository.save(order);
        log.info("OrderCreatedEvent - Orders Created with Id= {}",event.getOrderId());
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        if(orderRepository.findById(event.getOrderId()).isEmpty()) {
            log.error("OrderCompletedEvent - {} {}",Constants.ORDER_NOT_FOUND_WITH_ID, event.getOrderId());
            return;
        }
        Orders order
                = orderRepository.findById(event.getOrderId()).get();

        order.setOrderStatus(event.getOrderStatus());

        orderRepository.save(order);    

    }

    @EventHandler
    public void on(OrderCancelledEvent event) {

        if(orderRepository.findById(event.getOrderId()).isEmpty()) {
            log.error("OrderCancelledEvent - {} {}",Constants.ORDER_NOT_FOUND_WITH_ID, event.getOrderId());
            return;
        }

        Orders order
                = orderRepository.findById(event.getOrderId()).get();

        order.setOrderStatus(event.getOrderStatus());

        orderRepository.save(order);
    }

    @EventHandler
    public void on(UpdateOrderEvent event) {

        if(orderRepository.findById(event.getOrderId()).isEmpty()) {
            log.error("UpdateOrderEvent - {} {}",Constants.ORDER_NOT_FOUND_WITH_ID, event.getOrderId());
            return;
        }

        Orders order = new Orders();
        BeanUtils.copyProperties(event,order);
        orderRepository.save(order);
        log.info("UpdateOrderEvent - Orders Updated with Id= {}",event.getOrderId());

    }

}
