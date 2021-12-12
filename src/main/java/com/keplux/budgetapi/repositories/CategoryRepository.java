package com.keplux.budgetapi.repositories;

import com.keplux.budgetapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
