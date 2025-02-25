package com.happydev.foodcosaga.FoodCoSaga.CustomerService.cardDetail;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_API)
public class CardDetailController {
    private final CardDetailService cardDetailService;

    @Autowired
    public CardDetailController(CardDetailService cardDetail) {
        this.cardDetailService = cardDetail;
    }

    @CrossOrigin
    @GetMapping(Constants.URL_CARD_DETAIL)
    public ResponseEntity<List<CardDetail>> getCardDetails() {
        return ResponseEntity.ok(cardDetailService.getCardDetails());
    }

    @CrossOrigin
    @PostMapping(Constants.URL_CARD_DETAIL)
    public ResponseEntity<String> createCardDetail(@RequestBody @Valid CardDetail cardDetail) {
        String cardDetailId = cardDetailService.createCardDetail(cardDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardDetailId);
    }

    @CrossOrigin
    @GetMapping(Constants.URL_CARD_DETAIL+"/{cardDetailId}")
    public ResponseEntity<CardDetail> getCardDetail(@PathVariable String cardDetailId) throws CustomMessageException {
        return ResponseEntity.ok(cardDetailService.getCardDetail(cardDetailId));
    }

    @CrossOrigin
    @DeleteMapping(Constants.URL_CARD_DETAIL+"/{cardDetailId}")
    public ResponseEntity<String> removeCardDetail(@PathVariable String cardDetailId) throws CustomMessageException {
        return ResponseEntity.ok(cardDetailService.removeCardDetail(cardDetailId));
    }

    @CrossOrigin
    @PutMapping(Constants.URL_CARD_DETAIL)
    public ResponseEntity<String> updateCardDetail(@RequestBody @Valid CardDetail cardDetail) throws CustomMessageException {
        return ResponseEntity.ok(cardDetailService.updateCardDetail(cardDetail));
    }
}
