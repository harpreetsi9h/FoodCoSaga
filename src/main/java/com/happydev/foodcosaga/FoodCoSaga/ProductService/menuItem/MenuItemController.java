package com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class MenuItemController {

    private final MenuItemService itemService;

    @Autowired
    public MenuItemController(MenuItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @GetMapping(Constants.URL_MENU_ITEM)
    public ResponseEntity<List<MenuItem>> getMenuItems() {
        return ResponseEntity.ok(itemService.getMenuItems());
    }

    @CrossOrigin
    @PostMapping(Constants.URL_MENU_ITEM)
    public ResponseEntity<String> createMenuItem(@RequestBody @Valid MenuItem menuItem) {
        String menuItemId = itemService.createMenuItem(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemId);
    }

//    @GetMapping(Constants.URL_MENU_ITEM+"/{menuItemId}")
//    public ResponseEntity<MenuItem> getMenuItem(@PathVariable String menuItemId) throws CustomMessageException {
//        return ResponseEntity.ok(itemService.getMenuItem(menuItemId));
//    }

    @CrossOrigin
    @DeleteMapping(Constants.URL_MENU_ITEM+"/{menuItemId}")
    public ResponseEntity<String> removeMenuItem(@PathVariable String menuItemId) throws CustomMessageException {
        return ResponseEntity.ok(itemService.removeMenuItem(menuItemId));
    }

    @CrossOrigin
    @PutMapping(Constants.URL_MENU_ITEM)
    public ResponseEntity<String> updatedMenuItem(@RequestBody @Valid MenuItem menuItem) throws CustomMessageException {
        return ResponseEntity.ok(itemService.updateMenuItem(menuItem));
    }

//    @GetMapping(Constants.URL_MENU_ITEM+Constants.URL_RESTAURANT+"/{restId}")
//    public ResponseEntity<List<MenuItem>> getMenuItemsByRestId(@PathVariable String restId) {
//        return ResponseEntity.ok(itemService.getMenuItemsByRestId(restId));
//    }


}
