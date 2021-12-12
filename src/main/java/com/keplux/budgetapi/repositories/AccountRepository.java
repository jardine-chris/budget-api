package com.keplux.budgetapi.repositories;

import com.keplux.budgetapi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
