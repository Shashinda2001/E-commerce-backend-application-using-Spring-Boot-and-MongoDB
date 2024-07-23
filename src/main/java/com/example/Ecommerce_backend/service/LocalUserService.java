package com.example.Ecommerce_backend.service;

import com.example.Ecommerce_backend.api.model.LoginBody;
import com.example.Ecommerce_backend.api.model.RegistationBody;
import com.example.Ecommerce_backend.exception.UserAlreadyExistException;
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
    private EncryptionService encryptionService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private JWTservice jwTservice;

    public void createUser(RegistationBody registationBody) throws UserAlreadyExistException {

        if(localUserRepository.findByEmail(registationBody.getEmail()).isPresent()
                || localUserRepository.findByUsername(registationBody.getUsername()).isPresent()){
      throw new UserAlreadyExistException();
        }

        LocalUser user = new LocalUser();
        user.setUsername(registationBody.getUsername());
        user.setEmail(registationBody.getEmail());
        user.setFirstName(registationBody.getFirstName());
        user.setLastName(registationBody.getLastName());

        //todo hash password
        user.setPassword(encryptionService.encryptPassword(registationBody.getPassword()));


        localUserRepository.save(user);
    }

     //LOGIN USER
     public  String loginUser(LoginBody loginBody){
        Optional<LocalUser> opUser= localUserRepository.findByUsername(loginBody.getUsername());

        if(opUser.isPresent()){
            LocalUser user =opUser.get();

            if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
               return jwTservice.generateJWT(user);
            }
        }
        return  null;
     }

    public Optional<LocalUser> getUserById(String id) {
        return localUserRepository.findById(id);
    }

    public void deleteUserById(String id) {
        localUserRepository.deleteById(id);
    }

    public void updateUser(RegistationBody registationBody, String userId) throws UserAlreadyExistException {

        if(localUserRepository.findByEmail(registationBody.getEmail()).isPresent()
                || localUserRepository.findByUsername(registationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistException();
        }
        LocalUser user = localUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("LocalUser not found with id: " + userId));


        user.setUsername(registationBody.getUsername());
        user.setEmail(registationBody.getEmail());
        user.setFirstName(registationBody.getFirstName());
        user.setLastName(registationBody.getLastName());

        //todo hash password
        user.setPassword(encryptionService.encryptPassword(registationBody.getPassword()));


        // Save the updated LocalUser (this cascades to save the Address as well)
        localUserRepository.save(user);

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
