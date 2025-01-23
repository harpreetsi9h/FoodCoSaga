package com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.MenuItem;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.MenuItemRepository;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries.GetItemByIDQuery;
import com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries.GetItemsByRestaurant;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MenuItemProjection {

    private MenuItemRepository repository;

    public MenuItemProjection(MenuItemRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public MenuItem handle(GetItemByIDQuery query) {
        if(repository.findById(query.getMenuItemId()).isEmpty()){
            log.error("GetItemByIDQuery - {} {}", Constants.MENU_ITEM_NOT_FOUND_WITH_ID, query.getMenuItemId());
            return null;
        }
        return repository.findById(query.getMenuItemId()).get();
    }

    @QueryHandler
    public List<MenuItem> handle(GetItemsByRestaurant query) {
        return repository.findAll().stream()
                .filter(mi -> mi.getRestId().equals(query.getRestId()))
                .toList();
    }
}
