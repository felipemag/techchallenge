package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.valueobjects.CustomerVO;
import com.fiap.techchallenge.fourlanches.domain.aggregates.CustomerAggregate;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private CustomerAggregate customerAggregate;

    @GetMapping(value = "/{document}", produces = "application/json")
    public Customer getCustomerByDocument(@PathVariable String document) {
        return customerAggregate.getCustomerByDocument(document);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Customer saveCustomer(@RequestBody @Valid CustomerVO customer) {

        return customerAggregate.saveCustomer(customer);
    }
}
