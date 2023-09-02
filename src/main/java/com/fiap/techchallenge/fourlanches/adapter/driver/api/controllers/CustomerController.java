package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.application.dto.CustomerDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import com.fiap.techchallenge.fourlanches.domain.usecases.CustomerUseCase;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerUseCase customerUseCase;

    @Autowired
    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @GetMapping(value = "/{document}", produces = "application/json")
    public Customer getCustomerByDocument(@PathVariable String document) {
        return customerUseCase.getCustomerByDocument(document);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Customer saveCustomer(@RequestBody @Valid CustomerDTO customer) {

        return customerUseCase.saveCustomer(customer);
    }
}
