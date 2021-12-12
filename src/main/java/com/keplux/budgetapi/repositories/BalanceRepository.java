package com.keplux.budgetapi.repositories;

import com.keplux.budgetapi.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, UUID> {
}
