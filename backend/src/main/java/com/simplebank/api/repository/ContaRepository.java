package com.simplebank.api.repository;

import com.simplebank.api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    // Só de herdar o JpaRepository, você já ganha métodos como:
    // save(), findById(), findAll(), deleteById(), etc.
}