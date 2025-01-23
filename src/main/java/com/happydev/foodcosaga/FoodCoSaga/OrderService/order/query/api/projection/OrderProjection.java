package com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.projection;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.Orders;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.core.data.OrderRepository;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.queries.GetOrderByIdQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.order.query.api.queries.GetOrderQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderProjection {

    private final OrderRepository repository;

    public OrderProjection(OrderRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<Orders> handle(GetOrderQuery query) {

        return repository.findAll();
    }

    @QueryHandler
    public Orders handle(GetOrderByIdQuery query) {
        if(repository.findById(query.getOrderId()).isEmpty()) {
            log.error("OrderUpdatedEvent - {} {}", Constants.ORDER_NOT_FOUND_WITH_ID, query.getOrderId());
            return null;
        }
        return repository.findById(query.getOrderId()).get();
    }

}
