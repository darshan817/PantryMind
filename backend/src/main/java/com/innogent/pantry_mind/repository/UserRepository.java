package com.innogent.pantry_mind.repository;

import com.innogent.pantry_mind.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role LEFT JOIN FETCH u.kitchen WHERE u.email = :email")
    Optional<User> findByEmailWithRoleAndKitchen(@Param("email") String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email = :email")
    Optional<User> findByEmailWithRole(@Param("email") String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.kitchen WHERE u.username = :username")
    Optional<User> findByUsernameWithKitchen(@Param("username") String username);
    
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}