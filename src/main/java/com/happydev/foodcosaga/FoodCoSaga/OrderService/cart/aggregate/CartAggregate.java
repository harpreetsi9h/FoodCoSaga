package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.aggregate;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.commands.CartCloseCommand;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.CartCloseEvent;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command.CreateCartCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command.UpdateCartCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event.CartCreatedEvent;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.event.UpdateCartEvent;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Aggregate
@NoArgsConstructor
public class CartAggregate {

    @AggregateIdentifier
    private String cartId;
    private String customerId;
    private Float subTotal;
    private String status;
    private List<String> orderItems;
    private String createdAt;

    @CommandHandler
    public CartAggregate(CreateCartCommand command) {
        CartCreatedEvent event = new CartCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CartCreatedEvent event) {
        this.cartId = event.getCartId();
        this.customerId = event.getCustomerId();
        this.subTotal = event.getSubTotal();
        this.status = event.getStatus();
        this.orderItems = event.getOrderItems();
        this.createdAt = event.getCreatedAt();
    }

    @CommandHandler
    public void handle(UpdateCartCommand command) {
        UpdateCartEvent event = new UpdateCartEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdateCartEvent event) {
        this.customerId = event.getCustomerId();
        this.subTotal = event.getSubTotal();
        this.status = event.getStatus();
        this.orderItems = event.getOrderItems();
        this.createdAt = event.getCreatedAt();
    }

    @CommandHandler
    public void handle(CartCloseCommand command) {
        CartCloseEvent event = new CartCloseEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CartCloseEvent event) {
        this.status = event.getStatus();
    }

}
