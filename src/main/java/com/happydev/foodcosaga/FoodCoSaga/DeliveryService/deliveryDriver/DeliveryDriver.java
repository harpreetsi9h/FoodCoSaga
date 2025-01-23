package com.happydev.foodcosaga.FoodCoSaga.DeliveryService.deliveryDriver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.value.qual.MinLen;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDriver {

    @Id
    private String driverId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String addressId;
    @NotBlank
    private String email;
    @MinLen(10)
    private String phone;
    private String pic;
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Float rating;
    private Integer numberOfDeliveries;
    private String createdAt;
}
