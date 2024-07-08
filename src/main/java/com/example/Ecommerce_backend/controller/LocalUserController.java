package com.example.Ecommerce_backend.controller;

import com.example.Ecommerce_backend.model.Address;
import com.example.Ecommerce_backend.model.LocalUser;
import com.example.Ecommerce_backend.service.LocalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//@CrossOrigin(origins = "http://localhost:4200")  Allow specific origin for this controller
//@RequestMapping("/api/v1/")
@RestController
@RequestMapping("/users")
public class LocalUserController {

    @Autowired
    private LocalUserService localUserService;

    @PostMapping
    public LocalUser createUser(@RequestBody LocalUser user) {
        // Create user without addresses initially
        return localUserService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<LocalUser> getUserById(@PathVariable String id) {
        return localUserService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
        localUserService.deleteUserById(id);
    }

    @PutMapping
    public LocalUser updateUser(@RequestBody LocalUser user) {
        return localUserService.updateUser(user);
    }

    @PostMapping("/{userId}/addresses")
    public void addAddressToLocalUser(@PathVariable String userId, @RequestBody Address address) {
        localUserService.addAddressToLocalUser(userId, address);
    }
}
