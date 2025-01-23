package com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.projection;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItem;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.data.OrderItemRepository;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemById;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemsByIds;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderItemProjection {

    private final OrderItemRepository repository;

    public OrderItemProjection(OrderItemRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<OrderItemModel> handle(GetOrderItemQuery query) {
        List<OrderItem> orderItemList = repository.findAll();

        return orderItemList.stream().map(
                orderItem -> OrderItemModel.builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .menuItemId(orderItem.getMenuItemId())
                        .quantity(orderItem.getQuantity())
                        .createdAt(orderItem.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    @QueryHandler
    public OrderItemModel handle(GetOrderItemById query) {
        if(repository.findById(query.getOrderItemId()).isEmpty()) {
            log.error("GetOrderItemById - {} {}", Constants.ORDER_ITEM_NOT_FOUND_WITH_ID, query.getOrderItemId());
            return null;
        }
        OrderItem orderItem = repository.findById(query.getOrderItemId()).get();
        return OrderItemModel.builder()
                .orderItemId(orderItem.getOrderItemId())
                .menuItemId(orderItem.getMenuItemId())
                .quantity(orderItem.getQuantity())
                .createdAt(orderItem.getCreatedAt())
                .build();
    }

    @QueryHandler
    public List<OrderItemModel> handle(GetOrderItemsByIds query) {
        List<OrderItem> orderItemList = repository.findAllById(query.getOrderItemIds());

        return orderItemList.stream().map(
                orderItem -> OrderItemModel.builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .menuItemId(orderItem.getMenuItemId())
                        .quantity(orderItem.getQuantity())
                        .createdAt(orderItem.getCreatedAt())
                        .build()
        ).collect(Collectors.toList());
    }
}
