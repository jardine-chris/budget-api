package com.keplux.budgetapi.repositories;

import com.keplux.budgetapi.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,
        UUID> {
}
