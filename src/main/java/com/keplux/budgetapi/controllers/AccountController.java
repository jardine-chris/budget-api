package com.keplux.budgetapi.controllers;

import com.keplux.budgetapi.entities.Account;
import com.keplux.budgetapi.entities.Balance;
import com.keplux.budgetapi.entities.Budget;
import com.keplux.budgetapi.entities.Transaction;
import com.keplux.budgetapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * {Description}
 *
 * @author Chris Jardine
 * @version 0.1
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionModel<Account>> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<Account> getAccountById(@PathVariable UUID id) {
        return service.getAccountById(id);
    }

    @GetMapping(value = "/{accountId}/budget", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Budget> getAccountBudget(@PathVariable UUID accountId) {
        return service.getAccountBudget(accountId);
    }

    @GetMapping(value = "/{accountId}/balance", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Balance> getAccountBalance(@PathVariable UUID accountId) {
        return service.getAccountBalance(accountId);
    }

    @GetMapping(value = "/{accountId}/transactions", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable UUID accountId) {
        return service.getAccountTransactions(accountId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }
}
