package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.aggregate;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.commands.CancelOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.commands.CompleteOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.OrderCancelledEvent;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.OrderCompletedEvent;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.command.CreateOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.command.UpdateOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.events.OrderCreatedEvent;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.events.UpdateOrderEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String customerId;
    private Float subTotal;
    private String cartId;
    private Float serviceFee;
    private Float deliveryFee;
    private Float totalAmount;
    private String orderStatus;
    private String createdAt;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        //Validate The Command
        OrderCreatedEvent orderCreatedEvent
                = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,
                orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.customerId = event.getCustomerId();
        this.subTotal = event.getSubTotal();
        this.cartId = event.getCartId();
        this.serviceFee = event.getServiceFee();
        this.deliveryFee = event.getDeliveryFee();
        this.totalAmount = event.getTotalAmount();
        this.orderStatus = event.getOrderStatus();
        this.createdAt = event.getCreatedAt();
    }

    @CommandHandler
    public void handle(CompleteOrderCommand completeOrderCommand) {
        //Validate The Command
        // Publish Orders Completed Event
        OrderCompletedEvent orderCompletedEvent
                = OrderCompletedEvent.builder()
                .orderStatus(completeOrderCommand.getOrderStatus())
                .orderId(completeOrderCommand.getOrderId())
                .build();
        AggregateLifecycle.apply(orderCompletedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(CancelOrderCommand cancelOrderCommand) {
        OrderCancelledEvent orderCancelledEvent
                = new OrderCancelledEvent();
        BeanUtils.copyProperties(cancelOrderCommand,
                orderCancelledEvent);

        AggregateLifecycle.apply(orderCancelledEvent);
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(UpdateOrderCommand command) {
        UpdateOrderEvent event = new UpdateOrderEvent();
        BeanUtils.copyProperties(event, command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdateOrderCommand event) {
        this.customerId = event.getCustomerId();
        this.subTotal = event.getSubTotal();
        this.cartId = event.getCartId();
        this.serviceFee = event.getServiceFee();
        this.deliveryFee = event.getDeliveryFee();
        this.totalAmount = event.getTotalAmount();
        this.orderStatus = event.getOrderStatus();
        this.createdAt = event.getCreatedAt();
    }
}
