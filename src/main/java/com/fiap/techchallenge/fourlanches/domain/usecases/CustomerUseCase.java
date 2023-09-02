package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.CustomerDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;

public interface CustomerUseCase {
    Customer getCustomerByDocument(String document);
    Customer saveCustomer(CustomerDTO customerDTO);
}
