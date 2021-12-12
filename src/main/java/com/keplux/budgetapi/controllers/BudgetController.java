package com.keplux.budgetapi.controllers;

import com.keplux.budgetapi.entities.Account;
import com.keplux.budgetapi.entities.Budget;
import com.keplux.budgetapi.entities.Category;
import com.keplux.budgetapi.services.BudgetService;
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
@RequestMapping("/api/v1/budget")
public class BudgetController {

    @Autowired
    private BudgetService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionModel<Budget>> getAllBudgets() {
        return service.getAllBudgets();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<Budget> getBudgetById(@PathVariable UUID id) {
        return service.getBudgetById(id);
    }

    @GetMapping(value = "/{budgetId}/accounts", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionModel<Account>> getBudgetAccounts(@PathVariable UUID budgetId) {
        return service.getBudgetAccounts(budgetId);
    }

    @GetMapping(value = "/{budgetId}/categories", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Category>> getBudgetCategories(@PathVariable UUID budgetId) {
        return service.getBudgetCategories(budgetId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Budget> createBudget(@RequestBody Budget newBudget) {
        return service.createBudget(newBudget);
    }
}
