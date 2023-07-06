package com.fiap.techchallenge.fourlanches.adapter.driven.data.repositories;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.CustomerJpaEntity;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerJpaRepository;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import com.fiap.techchallenge.fourlanches.application.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.domain.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private CustomerJpaRepository jpaRepository;

    @Override
    public Customer getCustomerByDocument(String document) {
        return jpaRepository.findByDocument(document)
                .orElseThrow(CustomerNotFoundException::new)
                .getCustomerEntity();
    }

    @Override
    public Customer saveCustomer(CustomerJpaEntity customer) {
        return jpaRepository.save(customer)
                .getCustomerEntity();
    }
}
