package com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.MenuItem;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries.GetItemByIDQuery;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries.GetItemsByRestaurant;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class MenuItemQueryController {

    private final QueryGateway queryGateway;

    public MenuItemQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(Constants.URL_MENU_ITEM+"/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable String menuItemId) {
        GetItemByIDQuery query = new GetItemByIDQuery(menuItemId);
        MenuItem menuItem = queryGateway.query(query, ResponseTypes.instanceOf(MenuItem.class)).join();
        return ResponseEntity.ok(menuItem);
    }

    @GetMapping(Constants.URL_MENU_ITEM+Constants.URL_RESTAURANT+"/{restId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestId(@PathVariable String restId) {
        GetItemsByRestaurant query = new GetItemsByRestaurant(restId);
        List<MenuItem> menuItems = queryGateway.query(query, ResponseTypes.multipleInstancesOf(MenuItem.class)).join();
        return ResponseEntity.ok(menuItems);
    }
}
