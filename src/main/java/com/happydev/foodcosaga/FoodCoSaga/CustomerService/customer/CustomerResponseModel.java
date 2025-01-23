package com.happydev.foodcosaga.FoodCoSaga.CustomerService.customer;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.address.Address;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail.CardDetail;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.value.qual.MinLen;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseModel {

    @Id
    private String customerId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private Address address;
    @NotBlank
    private String email;
    @MinLen(10)
    private String phone;
    private CardDetail cardDetail;
    private String pic;
    private String createdAt;
}
