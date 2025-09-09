package com.ytechtrade.inventorymanagementsystem.repositories;

import com.ytechtrade.inventorymanagementsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
}
