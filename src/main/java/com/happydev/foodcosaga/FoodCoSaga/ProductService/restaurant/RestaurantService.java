package com.happydev.foodcosaga.FoodCoSaga.ProductService.restaurant;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.InternalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final InternalServices internalServices;

    @Autowired
    public RestaurantService(RestaurantRepository repository, InternalServices internalServices) {
        this.repository = repository;
        this.internalServices = internalServices;
    }

    public String createRestaurant(Restaurant restaurant) {
        String restId = UUID.randomUUID().toString();
        restaurant.setRestId(restId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        restaurant.setCreatedAt(timestamp.toString());
        repository.save(restaurant);
        return restId;
    }

    public String removeRestaurant(String restId) throws CustomMessageException {
        if (repository.findById(restId).isPresent()) {
            repository.deleteById(restId);
            return Constants.RESTAURANT_REMOVED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.RESTAURANT_NOT_FOUND_WITH_ID+restId);
    }

    public List<RestaurantReponseModel> getAllRestaurants() {
        List<Restaurant> dataList = repository.findAll();

        return dataList.stream().map(
        data -> RestaurantReponseModel.builder()
                .restId(data.getRestId())
                .name(data.getName())
                .cuisine(data.getCuisine())
                .rating(data.getRating())
                .address(internalServices.getAddress(data.getAddressId()))
                .closingTime(data.getClosingTime())
                .openingTime(data.getOpeningTime())
                .coverPic(data.getCoverPic())
                .logoPic(data.getLogoPic())
                .createdAt(data.getCreatedAt())
                .build()
        ).collect(Collectors.toList());
    }

    public RestaurantReponseModel getRestaurant(String restId) throws CustomMessageException {
        if(repository.findById(restId).isEmpty())
            throw new CustomMessageException(Constants.RESTAURANT_NOT_FOUND_WITH_ID+restId);

        Restaurant data = repository.findById(restId).get();

        return new RestaurantReponseModel().builder()
                .restId(data.getRestId())
                .name(data.getName())
                .cuisine(data.getCuisine())
                .rating(data.getRating())
                .address(internalServices.getAddress(data.getAddressId()))
                .closingTime(data.getClosingTime())
                .openingTime(data.getOpeningTime())
                .coverPic(data.getCoverPic())
                .logoPic(data.getLogoPic())
                .createdAt(data.getCreatedAt())
                .build();
    }

    public String updateRestaurant(Restaurant restaurant) throws CustomMessageException {

        if(repository.findById(restaurant.getRestId()).isPresent()) {
            repository.save(restaurant);
            return Constants.RESTAURANT_UPDATED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.RESTAURANT_NOT_FOUND_WITH_ID+restaurant.getRestId());
    }
}
