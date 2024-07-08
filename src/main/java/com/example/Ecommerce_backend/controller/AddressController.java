package com.example.Ecommerce_backend.controller;

import com.example.Ecommerce_backend.model.Address;
import com.example.Ecommerce_backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{userId}")
    public Address createAddress(@RequestBody Address address, @PathVariable String userId) {
        return addressService.createAddress(address, userId);
    }

    @GetMapping("/{id}")
    public Optional<Address> getAddressById(@PathVariable String id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable String id) {
        addressService.deleteAddressById(id);
    }

    @PutMapping("/{userId}")
    public Address updateAddress(@RequestBody Address address, @PathVariable String userId) {
        return addressService.updateAddress(address, userId);
    }
}
