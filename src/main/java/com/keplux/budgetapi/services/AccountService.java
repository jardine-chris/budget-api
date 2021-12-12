package com.keplux.budgetapi.services;

import com.keplux.budgetapi.controllers.AccountController;
import com.keplux.budgetapi.controllers.BudgetController;
import com.keplux.budgetapi.entities.Account;
import com.keplux.budgetapi.entities.Balance;
import com.keplux.budgetapi.entities.Budget;
import com.keplux.budgetapi.entities.Transaction;
import com.keplux.budgetapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * {Description}
 *
 * @author Chris Jardine
 * @version 0.1
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public ResponseEntity<CollectionModel<Account>> getAllAccounts() {
        List<Account> accounts = repository.findAll();
        accounts.forEach(account -> {
            account.add(linkTo(methodOn(AccountController.class)
                    .getAccountById(account.getId()))
                    .withSelfRel());
            account.add(linkTo(methodOn(AccountController.class)
                    .getAccountBudget(account.getId()))
                    .withRel("budget"));
            account.add(linkTo(methodOn(AccountController.class)
                    .getAccountBalance(account.getId()))
                    .withRel("balance"));
            account.add(linkTo(methodOn(AccountController.class)
                    .getAccountTransactions(account.getId()))
                    .withRel("balance"));
        });
        Link allAccountsLink =
                linkTo(methodOn(AccountController.class)
                        .getAllAccounts())
                        .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(accounts, allAccountsLink));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<Account> getAccountById(@PathVariable UUID id) {
        Account account =
                repository.findById(id).orElseThrow(RuntimeException::new);
        account.add(linkTo(methodOn(AccountController.class)
                .getAccountById(id))
                .withSelfRel());
        account.add(linkTo(methodOn(AccountController.class)
                .getAccountBudget(account.getId()))
                .withRel("budget"));
        account.add(linkTo(methodOn(AccountController.class)
                .getAccountBalance(account.getId()))
                .withRel("balance"));
        account.add(linkTo(methodOn(AccountController.class)
                .getAccountTransactions(account.getId()))
                .withRel("balance"));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountId}/budget", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Budget> getAccountBudget(@PathVariable UUID accountId) {
        Budget budget = repository.getById(accountId).getBudget();
        budget.add(linkTo(methodOn(BudgetController.class)
                .getBudgetById(accountId))
                .withSelfRel());
        budget.add(linkTo(methodOn(AccountController.class)
                .getAccountById(accountId)).withRel("account"));
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountId}/balance", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Balance> getAccountBalance(@PathVariable UUID accountId) {
        Balance balance = repository.getById(accountId).getBalance();
        balance.add(linkTo(methodOn(AccountController.class)
                .getAccountBudget(accountId))
                .withRel("budget"));
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountId}/transactions", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable UUID accountId) {
        List<Transaction> transactions =
                repository.getById(accountId).getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(repository.save(account),
                HttpStatus.OK);
    }
}
