package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.command.api.aggregate;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.commands.DeliverOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.OrderDeliveredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DeliveryAggregate {

    @AggregateIdentifier
    private String deliveryId;
    private String orderId;
    private String deliveryDriverId;
    private String deliveryStatus;

    public DeliveryAggregate() {
    }

    @CommandHandler
    public DeliveryAggregate(DeliverOrderCommand deliverOrderCommand) {
        //Validate the Command
        // Publish the Orders Shipped event
        OrderDeliveredEvent orderDeliveredEvent
                = OrderDeliveredEvent
                .builder()
                .deliveryId(deliverOrderCommand.getDeliveryId())
                .deliveryDriverId(deliverOrderCommand.getDeliveryDriverId())
                .orderId(deliverOrderCommand.getOrderId())
                .deliveryStatus("COMPLETED")
                .build();

        AggregateLifecycle.apply(orderDeliveredEvent);
    }

    @EventSourcingHandler
    public void on(OrderDeliveredEvent event) {
        this.orderId = event.getOrderId();
        this.deliveryId = event.getDeliveryId();
        this.deliveryDriverId = event.getDeliveryDriverId();
        this.deliveryStatus = event.getDeliveryStatus();
    }
}
