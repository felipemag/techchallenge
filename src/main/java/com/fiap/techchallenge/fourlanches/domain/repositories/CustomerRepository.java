package com.fiap.techchallenge.fourlanches.domain.repositories;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerJpaEntity;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository {

    Customer getCustomerByDocument(String document);

    Customer saveCustomer(CustomerJpaEntity customer);
}
