package com.fiap.techchallenge.fourlanches.domain.aggregates;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerSaveException;
import com.fiap.techchallenge.fourlanches.domain.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerAggregate {

    private final static String COULD_NOT_SAVE_MSG = "could not save customer";

    @Qualifier("customerRepository")
    CustomerRepository repository;

    public Customer getCustomerByDocument(String document) throws CustomerNotFoundException {
        return repository.getCustomerByDocument(document);
    }


    public Customer saveCustomer(CustomerDTO customerDTO) throws CustomerSaveException {
        try {
            return repository.saveCustomer(customerDTO.toEntity());
        } catch (IllegalArgumentException | OptimisticLockingFailureException | NullPointerException e) {
            log.error(COULD_NOT_SAVE_MSG, e);
            throw new CustomerSaveException(COULD_NOT_SAVE_MSG, e);
        }
    }
}
