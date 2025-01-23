package com.happydev.foodcosaga.FoodCoSaga.OrderService.saga;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.commands.*;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.*;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.CustomerResponseModel;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer.query.api.GetCustomerByIdQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;


    public OrderProcessingSaga() {
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Orders Id : {}",
                event.getOrderId());

        GetCustomerByIdQuery getCustomerByIdQuery =
                new GetCustomerByIdQuery(event.getCustomerId());

        CustomerResponseModel customer = null;

        try {
            customer = queryGateway.query(
                    getCustomerByIdQuery,
                    ResponseTypes.instanceOf(CustomerResponseModel.class)
            ).join();

        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
            cancelOrderCommand(event.getOrderId());
        }

        if (customer==null) {
            log.error("OrderProcessingSaga -> OrderCreatedEvent: Customer does not exist. Customer ID = {}, Orders ID = {}",
                    event.getCustomerId(), event.getOrderId());
            cancelOrderCommand(event.getOrderId());
            return;
        }

        ValidatePaymentCommand validatePaymentCommand
                = ValidatePaymentCommand
                .builder()
                .cardDetails(customer.getCardDetail())
                .orderId(event.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .cartId(event.getCartId())
                .build();

        commandGateway.sendAndWait(validatePaymentCommand);
    }

    private void cancelOrderCommand(String orderId) {
        CancelOrderCommand cancelOrderCommand
                = new CancelOrderCommand(orderId);
        commandGateway.send(cancelOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent in Saga for Orders Id : {}",
                event.getOrderId());
        try {

            DeliverOrderCommand deliverOrderCommand
                    = DeliverOrderCommand
                    .builder()
                    .deliveryId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();
            commandGateway.send(deliverOrderCommand);
            closeCartCommand(event.getCartId());
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
            cancelPaymentCommand(event);
        }
    }

    private void cancelPaymentCommand(PaymentProcessedEvent event) {
        CancelPaymentCommand cancelPaymentCommand
                = new CancelPaymentCommand(
                event.getPaymentId(), event.getOrderId()
        );

        commandGateway.send(cancelPaymentCommand);
    }

    private void closeCartCommand(String cartId) {
        CartCloseCommand command = new CartCloseCommand();
        command.setCartId(cartId);
        commandGateway.send(command);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderDeliveredEvent event) {

        log.info("OrderDeliveredEvent in Saga for Orders Id : {}",
                event.getOrderId());

        CompleteOrderCommand completeOrderCommand
                = CompleteOrderCommand.builder()
                .orderId(event.getOrderId())
                .orderStatus(Constants.STATUS_COMPLETE)
                .build();

        commandGateway.send(completeOrderCommand);

    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga for Orders Id : {}",
                event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga for Orders Id : {}",
                event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentCancelledEvent event) {
        log.info("PaymentCancelledEvent in Saga for Orders Id : {}",
                event.getOrderId());
        cancelOrderCommand(event.getOrderId());
    }
}
