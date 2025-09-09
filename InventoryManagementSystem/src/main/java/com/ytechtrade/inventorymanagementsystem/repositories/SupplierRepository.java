package com.ytechtrade.inventorymanagementsystem.repositories;

import com.ytechtrade.inventorymanagementsystem.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
