package com.keplux.budgetapi.repositories;

import com.keplux.budgetapi.entities.BudgetedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetedItemRepository extends JpaRepository<BudgetedItem,
        UUID> {
}
