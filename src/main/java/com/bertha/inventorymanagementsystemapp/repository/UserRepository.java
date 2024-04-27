package com.bertha.inventorymanagementsystemapp.repository;

import com.bertha.inventorymanagementsystemapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
}
