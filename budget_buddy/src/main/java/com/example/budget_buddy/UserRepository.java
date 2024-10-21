package com.example.budget_buddy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // <-- Wyszukiwanie użytkownika po e-mailu
}
