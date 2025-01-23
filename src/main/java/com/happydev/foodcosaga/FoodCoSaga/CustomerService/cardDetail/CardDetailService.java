package com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CardDetailService {

    private final CardDetailRepository repository;

    @Autowired
    public CardDetailService(CardDetailRepository repository) {
        this.repository = repository;
    }

    public List<CardDetail> getCardDetails() {
        return repository.findAll();
    }

    public String createCardDetail(CardDetail cardDetail) {
        cardDetail.setCardDetailId(UUID.randomUUID().toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cardDetail.setCreatedAt(timestamp.toString());
        repository.save(cardDetail);
        return cardDetail.getCardDetailId();
    }

    public String removeCardDetail(String cardDetailId) throws CustomMessageException {
        if (repository.findById(cardDetailId).isPresent()) {
            repository.deleteById(cardDetailId);
            return Constants.CARD_DETAIL_REMOVED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.CARD_DETAIL_NOT_FOUND_WITH_ID+cardDetailId);

    }

    public CardDetail getCardDetail(String cardDetailId) throws CustomMessageException {
        if(repository.findById(cardDetailId).isEmpty()) {
            log.error("GetCartDetailById - {} {}", Constants.CART_NOT_FOUND_WITH_ID, cardDetailId);
            return null;
        }
        return repository.findById(cardDetailId).get();
    }

    public String updateCardDetail(CardDetail cardDetail) throws CustomMessageException {

        if (repository.findById(cardDetail.getCardDetailId()).isPresent()) {
            repository.save(cardDetail);
            return Constants.CARD_DETAIL_UPDATED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.CARD_DETAIL_NOT_FOUND_WITH_ID+cardDetail.getCardDetailId());

    }
}
