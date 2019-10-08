package de.smava.homework.loanapplicationmicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.smava.homework.loanapplicationmicroservice.entity.LoanEntity;
import de.smava.homework.loanapplicationmicroservice.entity.UserEntity;

public interface LoanRepository extends JpaRepository<LoanEntity, String> {
    Optional<List<LoanEntity>> findByUser(UserEntity user);
}
