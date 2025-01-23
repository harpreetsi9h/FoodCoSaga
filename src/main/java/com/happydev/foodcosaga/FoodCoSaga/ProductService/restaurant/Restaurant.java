package com.happydev.foodcosaga.FoodCoSaga.ProductService.restaurant;

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
public class Restaurant {

    @Id
    private String restId;
    @NotBlank
    private String name;
    @NotBlank
    private String cuisine;
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Float rating;
    @NotBlank
    private String addressId;
    private String openingTime;
    private String closingTime;
    private String logoPic;
    private String coverPic;
    private String createdAt;
}
