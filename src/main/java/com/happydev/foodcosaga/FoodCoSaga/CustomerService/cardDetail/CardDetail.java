package com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class CardDetail {

    @Id
    private String cardDetailId;
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String untilMonth;
    @NotBlank
    private String untilYear;
    @NotBlank
    private String cardCvv;
    private String createdAt;
}
