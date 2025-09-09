package com.ytechtrade.inventorymanagementsystem.repositories;

import com.ytechtrade.inventorymanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
