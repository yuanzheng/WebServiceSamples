package com.ytechtrade.onlineshoppingsystem.repositories;

import com.ytechtrade.onlineshoppingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //类型为 Optional 的变量本身永远不应该为 null
    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
