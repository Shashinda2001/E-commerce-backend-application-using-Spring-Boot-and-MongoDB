package com.example.Ecommerce_backend.repository;


import com.example.Ecommerce_backend.model.WebOrderQuantities;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebOrderQuantitiesRepository extends MongoRepository<WebOrderQuantities, String> {
}
