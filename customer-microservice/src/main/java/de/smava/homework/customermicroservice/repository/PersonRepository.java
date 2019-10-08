package de.smava.homework.customermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smava.homework.customermicroservice.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {
}
