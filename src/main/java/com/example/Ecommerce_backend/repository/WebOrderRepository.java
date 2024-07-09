package com.example.Ecommerce_backend.repository;


import com.example.Ecommerce_backend.model.WebOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebOrderRepository extends MongoRepository<WebOrder, String> {
}
