package com.keplux.budgetapi.repositories;

import com.keplux.budgetapi.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
}
