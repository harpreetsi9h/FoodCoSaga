package com.happydev.foodcosaga.FoodCoSaga.CommonService.events;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import lombok.Data;

@Data
public class CartCloseEvent {
    private String cartId;
    private String status;
}
