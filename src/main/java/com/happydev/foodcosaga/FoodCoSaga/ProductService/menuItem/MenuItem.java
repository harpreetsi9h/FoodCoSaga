package com.happydev.foodcosaga.FoodCoSaga.ProductService.menuItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    private String menuItemId;
    @NotBlank
    private String restId;
    @NotBlank
    private String name;
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private float price;
    private String itemPic;
    private String desc;
    private String createdAt;
}
