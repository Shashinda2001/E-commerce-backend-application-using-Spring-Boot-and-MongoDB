package com.example.Ecommerce_backend.repository;

import com.example.Ecommerce_backend.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
