package com.keplux.budgetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.keplux.budgetapi.entities"})
public class BudgetApi {

    public static void main(String[] args) {
        SpringApplication.run(BudgetApi.class, args);
    }

}
