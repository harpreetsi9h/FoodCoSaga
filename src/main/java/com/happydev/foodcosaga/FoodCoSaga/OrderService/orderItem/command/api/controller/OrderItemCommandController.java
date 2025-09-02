package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.controller;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.commands.CreateOrderItemCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.command.api.commands.UpdateOrderItemCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping(Constants.URL_API)
public class OrderItemCommandController {

    private final CommandGateway commandGateway;

    public OrderItemCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(Constants.URL_ORDER_ITEM)
    public ResponseEntity<String> createOrderItem(@RequestBody @Valid OrderItemModel orderItem) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        CreateOrderItemCommand createOrderItemCommand = CreateOrderItemCommand.builder()
                .orderItemId(UUID.randomUUID().toString())
                .menuItemId(orderItem.getMenuItemId())
                .quantity(orderItem.getQuantity())
                .createdAt(timestamp.toString())
                .build();

        String result = commandGateway.sendAndWait(createOrderItemCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(Constants.URL_ORDER_ITEM)
    public String updateOrderItem(@RequestBody OrderItemModel orderItem) {
        UpdateOrderItemCommand command = UpdateOrderItemCommand.builder()
                .orderItemId(orderItem.getOrderItemId())
                .quantity(orderItem.getQuantity())
                .build();

        String result = commandGateway.sendAndWait(command);
        return Constants.ORDER_ITEM_UPDATED_SUCCESSFULLY;
    }
}
