package com.example.Ecommerce_backend.repository;

import com.example.Ecommerce_backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}