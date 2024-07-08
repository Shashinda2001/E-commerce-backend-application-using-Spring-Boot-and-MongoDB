package com.example.Ecommerce_backend.repository;

import com.example.Ecommerce_backend.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
