package com.example.Ecommerce_backend.controller;

import com.example.Ecommerce_backend.api.model.LoginBody;
import com.example.Ecommerce_backend.api.model.LoginResponse;
import com.example.Ecommerce_backend.api.model.RegistationBody;
import com.example.Ecommerce_backend.exception.UserAlreadyExistException;
import com.example.Ecommerce_backend.model.Address;
import com.example.Ecommerce_backend.model.LocalUser;
import com.example.Ecommerce_backend.service.LocalUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String>  createUser(@Valid @RequestBody RegistationBody user) {
         try {
            localUserService.createUser(user);
            return ResponseEntity.ok().build();
         }
         catch (UserAlreadyExistException ex){
             return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the given username or email already exists.");
         }
//        return localUserService.createUser(user);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt= localUserService.loginUser(loginBody);

        if(jwt==null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            LoginResponse response =new LoginResponse();
            response.setJwt(jwt);
            return  ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id}")
    public Optional<LocalUser> getUserById(@PathVariable String id) {
        return localUserService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
        localUserService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody RegistationBody user,@PathVariable String id) {
        try {
            localUserService.updateUser(user,id);
            return ResponseEntity.ok().build();
        }catch (UserAlreadyExistException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the given username or email already exists.");

        }
       // return localUserService.updateUser(user);
    }

    @PostMapping("/{userId}/addresses")
    public void addAddressToLocalUser(@PathVariable String userId, @RequestBody Address address) {
        localUserService.addAddressToLocalUser(userId, address);
    }
}
