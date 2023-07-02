package com.fiap.techchallenge.fourlanches.domain.aggregates;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerDTO;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerJpaEntity;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerRepositoryImpl;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerSaveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerAggregateTests {
    private CustomerAggregate customerAggregate;

    @Mock
    private CustomerRepositoryImpl customerRepository;

    @BeforeEach
    void init() {
        customerAggregate = new CustomerAggregate(customerRepository);
    }

    @Test
    void givenValidDocument_whenCustomerIsNotFound_thenThrowCustomerNotFoundException() {
        var notFoundDocument = "11122233300";
        when(customerRepository.getCustomerByDocument(notFoundDocument))
                .thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> {
            var customer = customerAggregate.getCustomerByDocument(notFoundDocument);
        });
    }

    @Test
    void givenValidDocument_whenCustomerIsFound_thenReturnCustomer() {
        var foundDocument = "00011122233";
        var expectedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(customerRepository.getCustomerByDocument(foundDocument))
                .thenReturn(expectedCustomer);

        var customer = customerRepository.getCustomerByDocument(foundDocument);
        assertEquals(customer, expectedCustomer);
    }

    @Test
    void givenNullCustomer_WhenSaveIsNotSuccessful_ThenThrowCustomerSaveException() {

        when(customerRepository.saveCustomer(null)).thenThrow(CustomerSaveException.class);

        assertThrows(CustomerSaveException.class, () -> {
            var customer = customerRepository.saveCustomer(null);
        });
    }

    @Test
    void givenValidCustomer_WhenSaveIsSuccessful_ThenReturnSavedCustomer() {
        var expectedCustomer = CustomerJpaEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(customerRepository.saveCustomer(expectedCustomer))
                .thenReturn(expectedCustomer.getCustomerEntity());

        var toSaveCustomerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        var customer = customerAggregate.saveCustomer(toSaveCustomerDTO);

        assertEquals(customer, expectedCustomer.getCustomerEntity());
    }
}
