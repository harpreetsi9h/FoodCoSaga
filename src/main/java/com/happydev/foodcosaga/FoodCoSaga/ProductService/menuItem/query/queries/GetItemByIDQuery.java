package com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetItemByIDQuery {

    private String menuItemId;
}
