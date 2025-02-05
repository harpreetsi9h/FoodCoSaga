package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.controller;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemById;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class OrderItemQueryController {

    private final QueryGateway queryGateway;

    public OrderItemQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @CrossOrigin
    @GetMapping(Constants.URL_ORDER_ITEM)
    public ResponseEntity<List<OrderItemModel>> getOrderItems() {

        GetOrderItemQuery getOrderItemQuery = new GetOrderItemQuery();

        List<OrderItemModel> orderItems =
                queryGateway.query(getOrderItemQuery, ResponseTypes.multipleInstancesOf(OrderItemModel.class)).join();
        return ResponseEntity.ok(orderItems);
    }

    @CrossOrigin
    @GetMapping(Constants.URL_ORDER_ITEM+"/{orderItemId}")
    public ResponseEntity<OrderItemModel> getOrderItem(@PathVariable String orderItemId) {

        GetOrderItemById getOrderItemQuery = new GetOrderItemById(orderItemId);

        OrderItemModel orderItem =
                queryGateway.query(getOrderItemQuery, ResponseTypes.instanceOf(OrderItemModel.class)).join();
        return ResponseEntity.ok(orderItem);
    }
}
