package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.controller;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.command.CreateOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.command.api.command.UpdateOrderCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.Orders;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping(Constants.URL_API)
public class OrderCommandController {

    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(Constants.URL_ORDER)
    public String createOrder(@RequestBody Orders order) {

        String orderId = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        CreateOrderCommand createOrderCommand
                = CreateOrderCommand.builder()
                .orderId(orderId)
                .createdAt(timestamp.toString())
                .customerId(order.getCustomerId())
                .cartId(order.getCartId())
                .subTotal(order.getSubTotal())
                .orderStatus(order.getOrderStatus())
                .deliveryFee(order.getDeliveryFee())
                .serviceFee(order.getServiceFee())
                .totalAmount(order.getTotalAmount())
                .build();

        commandGateway.sendAndWait(createOrderCommand);

        return orderId;
    }

    @PutMapping(Constants.URL_ORDER)
    public String updateOrder(@RequestBody Orders order) {
        UpdateOrderCommand updateOrderCommand
                = UpdateOrderCommand.builder()
                .orderId(order.getOrderId())
                .createdAt(order.getCreatedAt())
                .customerId(order.getCustomerId())
                .cartId(order.getCartId())
                .subTotal(order.getSubTotal())
                .orderStatus(order.getOrderStatus())
                .deliveryFee(order.getDeliveryFee())
                .serviceFee(order.getServiceFee())
                .totalAmount(order.getTotalAmount())
                .build();

        commandGateway.sendAndWait(updateOrderCommand);

        return Constants.ORDER_UPDATED_SUCCESSFULLY;
    }
}
