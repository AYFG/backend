package dev.ohhonim.ohho02.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ohhonim.ohho02.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByEmail(String email);
}
