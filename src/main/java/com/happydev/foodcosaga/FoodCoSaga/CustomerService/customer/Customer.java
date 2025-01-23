package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Customer {

    @Id
    private String customerId;
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
    private String cardDetailId;
    private String pic;
    private String createdAt;
}
