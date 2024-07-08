package com.example.Ecommerce_backend.service;

import com.example.Ecommerce_backend.model.Address;
import com.example.Ecommerce_backend.model.LocalUser;
import com.example.Ecommerce_backend.repository.AddressRepository;
import com.example.Ecommerce_backend.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LocalUserRepository localUserRepository;

    public Address createAddress(Address newAddress, String userId) {
        // Set userId from input
        newAddress.setUserId(userId);

        // Fetch the LocalUser entity
        LocalUser localUser = localUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("LocalUser not found with id: " + userId));

        // Remove existing address, if any
        localUser.getAddresses().removeIf(address -> address.getUserId().equals(userId));

        // Add the new address to the LocalUser's addresses list
        localUser.getAddresses().add(newAddress);

        // Save the updated LocalUser (this cascades to save the Address as well)
        localUserRepository.save(localUser);

        // Return the saved Address entity
        return addressRepository.save(newAddress);
    }


    public Optional<Address> getAddressById(String id) {
        return addressRepository.findById(id);
    }

    public void deleteAddressById(String id) {
        addressRepository.deleteById(id);
    }

    public Address updateAddress(Address address, String userId) {
        // Ensure userId is not changed unintentionally
        Address existingAddress = addressRepository.findById(address.getId())
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + address.getId()));

        if (!existingAddress.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to update this address");
        }

        // Fetch the LocalUser entity
        LocalUser localUser = localUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("LocalUser not found with id: " + userId));

        // Remove the existingAddress from localUser's addresses
        boolean removed = localUser.getAddresses().removeIf(addr -> addr.getId().equals(address.getId()));
        if (!removed) {
            throw new RuntimeException("Address not found in the user's address list");
        }

        // Add the updated address
        localUser.getAddresses().add(address);

        // Save the updated LocalUser (this cascades to save the Address as well)
        localUserRepository.save(localUser);

        // Set userId from input
        address.setUserId(userId);

        return addressRepository.save(address);
    }

}
