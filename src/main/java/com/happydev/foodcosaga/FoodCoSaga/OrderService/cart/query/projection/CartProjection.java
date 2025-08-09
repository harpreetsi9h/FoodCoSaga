package com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.projection;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.Cart;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.core.CartRepository;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartByCustomer;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartByIdQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.cart.query.queries.GetCartsQuery;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.core.model.OrderItemModel;
import com.happydev.foodcosaga.FoodCoSaga.OrderService.orderItem.query.api.queries.GetOrderItemsByIds;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.MenuItem;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries.GetItemByIDQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CartProjection {

    private final CartRepository repository;
    private final QueryGateway queryGateway;

    public CartProjection(CartRepository repository, QueryGateway queryGateway) {
        this.repository = repository;
        this.queryGateway = queryGateway;
    }

    @QueryHandler
    public Cart handle(GetCartByCustomer query) {
        List<Cart> carts = repository.findAll().stream()
                .filter(cart -> cart.getCustomerId().equals(query.getCustomerId()) &&
                        cart.getStatus().equals(Constants.STATUS_OPEN)).toList();
        if(carts.isEmpty()) {
            log.error("UpdateCartEvent - Cart not found with customer ID= {}", query.getCustomerId());
            return null;
        }
        return updateSubTotal(carts.get(0));
    }

    @QueryHandler
    public Cart handle(GetCartByIdQuery query) {
        if(repository.findById(query.getCartId()).isEmpty()) {
            log.error("GetCartByIdQuery - {} {}", Constants.CART_NOT_FOUND_WITH_ID, query.getCartId());
            return null;
        }
        return updateSubTotal(repository.findById(query.getCartId()).get());
    }

    @QueryHandler
    public List<Cart> handle(GetCartsQuery query) {
        return repository.findAll();
    }

    private Cart updateSubTotal(Cart cart) {
        float subTotal = getOrderItems(cart.getOrderItems())
                .stream()
                .map(oi -> oi.getQuantity() * getMenuItem(oi.getMenuItemId()).getPrice())
                .reduce(0.0f, Float::sum);
        if(subTotal!=cart.getSubTotal()) {
            cart.setSubTotal(subTotal);
            repository.save(cart);
        }
        return cart;
    }

    private List<OrderItemModel> getOrderItems(List<String> orderItems) {
        GetOrderItemsByIds query = new GetOrderItemsByIds(orderItems);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(OrderItemModel.class)).join();
    }

    private MenuItem getMenuItem(String menuItemId) {
        GetItemByIDQuery query = new GetItemByIDQuery(menuItemId);
        return queryGateway.query(query, ResponseTypes.instanceOf(MenuItem.class)).join();
    }
}
