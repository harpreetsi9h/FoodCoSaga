package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.controller;


import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.CountResModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.Orders;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.OrdersCountRes;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.queries.GetOrderByIdQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.queries.GetOrderQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.queries.GetOrdersCountQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class OrderQueryController {

    private final QueryGateway queryGateway;

    public OrderQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @CrossOrigin
    @GetMapping(Constants.URL_ORDER)
    public ResponseEntity<List<Orders>> getOrders() {
        GetOrderQuery getOrderQuery = new GetOrderQuery();

        List<Orders> order =
                queryGateway.query(getOrderQuery, ResponseTypes.multipleInstancesOf(Orders.class)).join();
        return ResponseEntity.ok(order);
    }

    @CrossOrigin
    @GetMapping(Constants.URL_ORDER+"/{orderId}")
    public ResponseEntity<Orders> getOrder(@PathVariable String orderId) {
        GetOrderByIdQuery query = new GetOrderByIdQuery();
        query.setOrderId(orderId);

        Orders order = queryGateway.query(query, ResponseTypes.instanceOf(Orders.class)).join();
        return ResponseEntity.ok(order);
    }

    @CrossOrigin
    @GetMapping(Constants.URL_ORDER+"/countByStatus/{status}")
    public ResponseEntity<OrdersCountRes> getOrdersCountByStatus(@PathVariable String status) {
        GetOrdersCountQuery query = new GetOrdersCountQuery();
        query.setOrderStatus(status);

        Long count = queryGateway.query(query, ResponseTypes.instanceOf(Long.class)).join();
        OrdersCountRes res = new OrdersCountRes(count, status);
        return ResponseEntity.ok(res);
    }
}
