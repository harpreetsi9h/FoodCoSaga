package com.happydev.foodcosaga.FoodCoSaga.CommonService.address;

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
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(Constants.URL_ADDRESS)
    public ResponseEntity<List<Address>> getAddresses() {
        return ResponseEntity.ok(addressService.getAddresses());
    }

    @PostMapping(Constants.URL_ADDRESS)
    public ResponseEntity<String> createAddress(@RequestBody @Valid Address address) {
        String addressId = addressService.createAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressId);
    }

    @GetMapping(Constants.URL_ADDRESS+"/{addressId}")
    public ResponseEntity<Address> getAddress(@PathVariable String addressId) throws CustomMessageException {
        return ResponseEntity.ok(addressService.getAddress(addressId));
    }

    @DeleteMapping(Constants.URL_ADDRESS+"/{addressId}")
    public ResponseEntity<String> removeAddress(@PathVariable String addressId) throws CustomMessageException {
        return ResponseEntity.ok(addressService.removeAddress(addressId));
    }

    @PutMapping(Constants.URL_ADDRESS)
    public ResponseEntity<String> updateAddress(@RequestBody @Valid Address address) throws CustomMessageException {
        return ResponseEntity.ok(addressService.updateAddress(address));
    }
}
