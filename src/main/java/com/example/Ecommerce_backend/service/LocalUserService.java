package com.example.Ecommerce_backend.service;

import com.example.Ecommerce_backend.model.Address;
import com.example.Ecommerce_backend.model.LocalUser;
import com.example.Ecommerce_backend.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalUserService {

    @Autowired
    private LocalUserRepository localUserRepository;

    @Autowired
    private AddressService addressService;

    public LocalUser createUser(LocalUser user) {
        // Save user first without addresses
        LocalUser savedUser = localUserRepository.save(user);
        return savedUser;
    }

    public Optional<LocalUser> getUserById(String id) {
        return localUserRepository.findById(id);
    }

    public void deleteUserById(String id) {
        localUserRepository.deleteById(id);
    }

    public LocalUser updateUser(LocalUser user) {
        return localUserRepository.save(user);
    }

    public void addAddressToLocalUser(String userId, Address address) {
        Optional<LocalUser> optionalUser = localUserRepository.findById(userId);
        if (optionalUser.isPresent()) {
            LocalUser user = optionalUser.get();
            address.setUserId(userId); // Set the userId in Address
            user.getAddresses().add(address); // Add address to user's addresses
            localUserRepository.save(user); // Save user with updated addresses
        } else {
            // Handle case where user with given userId is not found
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
}
