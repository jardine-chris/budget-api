package com.keplux.budgetapi.controllers;

import com.keplux.budgetapi.entities.Category;
import com.keplux.budgetapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * {Description}
 *
 * @author Chris Jardine
 * @version 0.1
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Category>> getAllAccounts() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Category> getBudgetById(@PathVariable UUID id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Category> createBudget(@RequestBody Category newCategory) {
        return new ResponseEntity<>(repository.save(newCategory),
                HttpStatus.OK);
    }
}
