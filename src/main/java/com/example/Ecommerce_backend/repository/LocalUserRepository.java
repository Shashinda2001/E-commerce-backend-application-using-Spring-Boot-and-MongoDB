package com.example.Ecommerce_backend.repository;

import com.example.Ecommerce_backend.model.LocalUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface LocalUserRepository extends MongoRepository<LocalUser, String> {
    Optional<LocalUser> findByUsername(String username);
    Optional<LocalUser> findByEmail(String email);
}
