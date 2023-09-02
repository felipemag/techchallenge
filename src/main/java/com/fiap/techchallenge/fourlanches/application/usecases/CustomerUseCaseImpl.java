package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.CustomerDTO;
import com.fiap.techchallenge.fourlanches.application.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.application.exception.CustomerSaveException;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import com.fiap.techchallenge.fourlanches.domain.repositories.CustomerRepository;
import com.fiap.techchallenge.fourlanches.domain.usecases.CustomerUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final static String COULD_NOT_SAVE_MSG = "could not save customer";

    private final CustomerRepository repository;

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
