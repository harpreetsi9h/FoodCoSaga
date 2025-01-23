package com.happydev.foodcosaga.FoodCoSaga.CommonService.address;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.exception.CustomMessageException;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAddresses() {
        return repository.findAll();
    }

    public String createAddress(Address address) {
        address.setAddressId(UUID.randomUUID().toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        address.setCreatedAt(timestamp.toString());
        repository.save(address);
        return address.getAddressId();
    }

    public String removeAddress(String addressId) throws CustomMessageException {
        if (repository.findById(addressId).isPresent()) {
            repository.deleteById(addressId);
            return Constants.ADDRESS_REMOVED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.ADDRESS_NOT_FOUND_WITH_ID+addressId);

    }

    public Address getAddress(String addressId) throws CustomMessageException {
        if(repository.findById(addressId).isEmpty())
            throw new CustomMessageException(Constants.ADDRESS_NOT_FOUND_WITH_ID+addressId);
        return repository.findById(addressId).get();
    }

    public String updateAddress(Address address) throws CustomMessageException {

        if (repository.findById(address.getAddressId()).isPresent()) {
            repository.save(address);
            return Constants.ADDRESS_UPDATED_SUCCESSFULLY;
        }
        else throw new CustomMessageException(Constants.ADDRESS_NOT_FOUND_WITH_ID+address.getAddressId());

    }
}
