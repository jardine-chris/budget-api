package com.keplux.budgetapi.services;

import com.keplux.budgetapi.controllers.AccountController;
import com.keplux.budgetapi.controllers.BudgetController;
import com.keplux.budgetapi.entities.Account;
import com.keplux.budgetapi.entities.Budget;
import com.keplux.budgetapi.entities.Category;
import com.keplux.budgetapi.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
public class BudgetService {

    @Autowired
    private BudgetRepository repository;

    public ResponseEntity<CollectionModel<Budget>> getAllBudgets() {
        List<Budget> budgets = repository.findAll();
        budgets.forEach(budget -> {
            budget.add(linkTo(methodOn(BudgetController.class)
                    .getBudgetById(budget.getId()))
                    .withSelfRel());
            budget.add(linkTo(methodOn(BudgetController.class)
                    .getBudgetAccounts(budget.getId()))
                    .withRel("accounts"));
            budget.add(linkTo(methodOn(BudgetController.class)
                    .getBudgetCategories(budget.getId()))
                    .withRel("categories"));
        });
        Link allBudgetsLink =
                linkTo(methodOn(BudgetController.class)
                        .getAllBudgets())
                        .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(budgets, allBudgetsLink));
    }

    public HttpEntity<Budget> getBudgetById(@PathVariable UUID id) {
        Budget budget =
                repository.findById(id).orElseThrow(RuntimeException::new);
        budget.add(linkTo(methodOn(BudgetController.class)
                .getBudgetById(id))
                .withSelfRel());
        budget.add(linkTo(methodOn(BudgetController.class)
                .getBudgetAccounts(budget.getId()))
                .withRel("accounts"));
        budget.add(linkTo(methodOn(BudgetController.class)
                .getBudgetCategories(budget.getId()))
                .withRel("categories"));
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    public ResponseEntity<CollectionModel<Account>> getBudgetAccounts(@PathVariable UUID budgetId) {
        List<Account> accounts = repository.getById(budgetId).getAccounts();
        accounts.forEach(account -> {
            account.add(linkTo(methodOn(AccountController.class)
                    .getAccountById(account.getId())).withRel("account"));
        });
        Link allAccountsLink =
                linkTo(methodOn(AccountController.class)
                        .getAllAccounts())
                        .withRel("accounts");
        return ResponseEntity.ok(CollectionModel.of(accounts, allAccountsLink));
    }

    public ResponseEntity<List<Category>> getBudgetCategories(@PathVariable UUID budgetId) {
        List<Category> categories =
                repository.getById(budgetId).getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<Budget> createBudget(@RequestBody Budget newBudget) {
        return new ResponseEntity<>(repository.save(newBudget),
                HttpStatus.OK);
    }
}
