package com.happydev.foodcosaga.FoodCoSaga.CommonService.util;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.address.Address;
import com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail.CardDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InternalServices {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Address getAddress(String addressId) {

        String apiUrl = Constants.URL_LOCAL_BASE+Constants.URL_API+Constants.URL_ADDRESS+"/"+addressId;
        return webClientBuilder.build()
                .get().uri(apiUrl).retrieve().bodyToMono(Address.class).block();
    }

    public CardDetail getCardDetail(String cardDetailId) {
        String apiUrl = Constants.URL_LOCAL_BASE+Constants.URL_API+Constants.URL_CARD_DETAIL+"/"+cardDetailId;
        return webClientBuilder.build()
                .get().uri(apiUrl).retrieve().bodyToMono(CardDetail.class).block();
    }
}
