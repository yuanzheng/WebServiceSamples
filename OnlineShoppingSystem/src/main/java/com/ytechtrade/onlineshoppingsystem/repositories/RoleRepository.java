package com.ytechtrade.onlineshoppingsystem.repositories;

import com.ytechtrade.onlineshoppingsystem.model.AppRole;
import com.ytechtrade.onlineshoppingsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
