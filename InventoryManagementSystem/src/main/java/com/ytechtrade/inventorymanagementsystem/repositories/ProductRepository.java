package com.ytechtrade.inventorymanagementsystem.repositories;

import com.ytechtrade.inventorymanagementsystem.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    // Find all non-deleted products
    List<Product> findByDeletedFalse(Sort sort);

    // Find non-deleted product by ID
    Optional<Product> findByIdAndDeletedFalse(Long id);

    // Search non-deleted products
    List<Product> findByDeletedFalseAndNameContainingOrDeletedFalseAndDescriptionContaining(String name, String description);
}
