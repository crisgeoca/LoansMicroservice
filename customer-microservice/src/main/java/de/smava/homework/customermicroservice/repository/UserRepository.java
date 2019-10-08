package de.smava.homework.customermicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smava.homework.customermicroservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
