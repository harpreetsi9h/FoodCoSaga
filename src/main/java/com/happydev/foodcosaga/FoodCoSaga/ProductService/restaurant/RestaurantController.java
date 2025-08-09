package com.happydev.foodcosaga.FoodCoSaga.ProductService.restaurant;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.CountResModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @CrossOrigin
    @GetMapping(Constants.URL_RESTAURANT)
    public ResponseEntity<List<RestaurantReponseModel>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @CrossOrigin
    @PostMapping(Constants.URL_RESTAURANT)
    public ResponseEntity<String> createRestaurant(@RequestBody @Valid Restaurant restaurant) {
        String restId = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restId);
    }

    @CrossOrigin
    @GetMapping(Constants.URL_RESTAURANT+"/{restId}")
    ResponseEntity<RestaurantReponseModel> getRestaurant(@PathVariable String restId) throws CustomMessageException {
        return ResponseEntity.ok(restaurantService.getRestaurant(restId));
    }

    @CrossOrigin
    @DeleteMapping(Constants.URL_RESTAURANT+"/{restId}")
    public ResponseEntity<String> removeRestaurant(@PathVariable String restId) throws CustomMessageException {
        return ResponseEntity.ok(restaurantService.removeRestaurant(restId));
    }

    @CrossOrigin
    @PutMapping(Constants.URL_RESTAURANT)
    public ResponseEntity<String> updateRestaurant(@RequestBody Restaurant restaurant) throws CustomMessageException {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurant));
    }

    @CrossOrigin
    @GetMapping(Constants.URL_RESTAURANT+"/count")
    public ResponseEntity<CountResModel> getRestCount() {
        return ResponseEntity.ok(restaurantService.getRestCount());
    }
}
