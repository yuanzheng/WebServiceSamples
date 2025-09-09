package com.ytechtrade.inventorymanagementsystem.repositories;

import com.ytechtrade.inventorymanagementsystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
