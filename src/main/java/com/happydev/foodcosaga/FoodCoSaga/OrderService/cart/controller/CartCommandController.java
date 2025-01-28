package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.controller;


import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command.AddOrderItemCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command.CreateCartCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.command.UpdateCartCommand;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.Cart;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping(Constants.URL_API)
public class CartCommandController {

    private final CommandGateway commandGateway;

    public CartCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(Constants.URL_CART)
    public String createCart(@RequestBody Cart cart) {

        String cartId = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        CreateCartCommand command = CreateCartCommand.builder()
                .cartId(cartId)
                .createdAt(timestamp.toString())
                .customerId(cart.getCustomerId())
                .status(Constants.STATUS_OPEN)
                .orderItems(cart.getOrderItems())
                .subTotal(cart.getSubTotal())
                .build();

        commandGateway.sendAndWait(command);
        return cartId;
    }

    @PutMapping(Constants.URL_CART)
    public String updateCart(@RequestBody Cart cart) {

        UpdateCartCommand command = UpdateCartCommand.builder()
                .cartId(cart.getCartId())
                .createdAt(cart.getCreatedAt())
                .customerId(cart.getCustomerId())
                .status(cart.getStatus())
                .orderItems(cart.getOrderItems())
                .subTotal(cart.getSubTotal())
                .build();

        commandGateway.sendAndWait(command);
        return Constants.CART_UPDATED_SUCCESSFULLY;
    }

    @PutMapping(Constants.URL_CART+"/orderItem")
    public String addOrderItems(@RequestBody Cart cart) {

        AddOrderItemCommand command = AddOrderItemCommand.builder()
                .cartId(cart.getCartId())
                .orderItems(cart.getOrderItems())
                .build();

        commandGateway.sendAndWait(command);
        return Constants.ORDER_ITEM_ADDED_TO_CART;
    }
}
