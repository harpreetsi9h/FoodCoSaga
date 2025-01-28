package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.aggregate;

import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.commands.CreateOrderItemCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.commands.UpdateOrderItemCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.events.OrderItemCreatedEvent;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.events.OrderItemUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderItemAggregate {

    @AggregateIdentifier
    private String orderItemId;
    private String menuItemId;
    private Integer quantity;
    private String createdAt;

    @CommandHandler
    public OrderItemAggregate(CreateOrderItemCommand createCommand) {

        OrderItemCreatedEvent createdEvent = new OrderItemCreatedEvent();

        BeanUtils.copyProperties(createCommand, createdEvent);

        AggregateLifecycle.apply(createdEvent);
    }

    public OrderItemAggregate() { }

    @EventSourcingHandler
    public void on(OrderItemCreatedEvent createdEvent) {
        this.orderItemId = createdEvent.getOrderItemId();
        this.menuItemId = createdEvent.getMenuItemId();
        this.quantity = createdEvent.getQuantity();
        this.createdAt = createdEvent.getCreatedAt();
    }

    @CommandHandler
    public void handle(UpdateOrderItemCommand command) {
        OrderItemUpdateEvent event = new OrderItemUpdateEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(OrderItemUpdateEvent event) {
        this.quantity = event.getQuantity();
    }
}
