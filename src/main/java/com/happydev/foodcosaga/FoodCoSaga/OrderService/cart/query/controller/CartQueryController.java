package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.controller;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.Cart;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.CartModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartByCustomer;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartByIdQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartsQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemsByIds;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class CartQueryController {

    private final QueryGateway queryGateway;

    public CartQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(Constants.URL_CART+"/id={customerId}")
    public ResponseEntity<CartModel> getCartByCustomer(@PathVariable String customerId) {
        GetCartByCustomer query = new GetCartByCustomer(customerId);

        Cart cart = queryGateway.query(query, ResponseTypes.instanceOf(Cart.class) ).join();
        CartModel cartModel = CartModel.builder()
                .cartId(cart.getCartId())
                .status(cart.getStatus())
                .customerId(cart.getCustomerId())
                .subTotal(cart.getSubTotal())
                .createdAt(cart.getCreatedAt())
                .orderItems(getOrderItems(cart.getOrderItems()))
                .build();
        return ResponseEntity.ok(cartModel);
    }

    @GetMapping(Constants.URL_CART+"/{cartId}")
    public ResponseEntity<CartModel> getCartByID(@PathVariable String cartId) {
        GetCartByIdQuery query = new GetCartByIdQuery(cartId);

        Cart cart = queryGateway.query(query, ResponseTypes.instanceOf(Cart.class)).join();
        CartModel cartModel = CartModel.builder()
                .cartId(cart.getCartId())
                .status(cart.getStatus())
                .customerId(cart.getCustomerId())
                .subTotal(cart.getSubTotal())
                .createdAt(cart.getCreatedAt())
                .orderItems(getOrderItems(cart.getOrderItems()))
                .build();

        return ResponseEntity.ok(cartModel);
    }

    @GetMapping(Constants.URL_CART)
    public ResponseEntity<List<CartModel>> getCarts() {
        GetCartsQuery query = new GetCartsQuery();

        List<Cart> carts = queryGateway.query(query, ResponseTypes.multipleInstancesOf(Cart.class)).join();

        List<CartModel> cartModels = carts.stream()
                .map(cart -> CartModel.builder()
                        .cartId(cart.getCartId())
                        .status(cart.getStatus())
                        .customerId(cart.getCustomerId())
                        .subTotal(cart.getSubTotal())
                        .createdAt(cart.getCreatedAt())
                        .orderItems(getOrderItems(cart.getOrderItems()))
                        .build()).toList();

        return ResponseEntity.ok(cartModels);
    }

    private List<OrderItemModel> getOrderItems(List<String> orderItems) {

        GetOrderItemsByIds query = new GetOrderItemsByIds(orderItems);

        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(OrderItemModel.class)).join();

    }
}
