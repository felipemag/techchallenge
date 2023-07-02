package com.fiap.techchallenge.fourlanches.driven.data;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerJpaEntity;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerJpaRepository;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.CustomerRepositoryImpl;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryImplTests {

    private CustomerRepositoryImpl customerRepository;

    @Mock
    private CustomerJpaRepository jpaRepository;

    @BeforeEach
    void init() {
        customerRepository = new CustomerRepositoryImpl(jpaRepository);
    }

    @Test
    void givenValidDocument_whenCustomerIsNotFound_thenThrowCustomerNotFoundException() {
        var notFoundDocument = "11122233300";
        when(jpaRepository.findByDocument(notFoundDocument))
                .thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class, () -> {
            var customer = customerRepository.getCustomerByDocument(notFoundDocument);
        });
    }

    @Test
    void givenValidDocument_whenCustomerIsFound_thenReturnCustomer() {
        var foundDocument = "00011122233";
        var expectedCustomer = CustomerJpaEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();
        var expected = Optional.of(expectedCustomer);

        when(jpaRepository.findByDocument(foundDocument))
                .thenReturn(expected);

        var customer = customerRepository.getCustomerByDocument(foundDocument);
        assertEquals(customer, expectedCustomer.getCustomerEntity());
    }

    @Test
    void givenValidCustomer_WhenSaveIsSuccessful_ThenReturnSavedCustomer() {
        var expectedCustomer = CustomerJpaEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(jpaRepository.save(expectedCustomer))
                .thenReturn(expectedCustomer);

        var customer = customerRepository.saveCustomer(expectedCustomer);

        assertEquals(customer, expectedCustomer.getCustomerEntity());
    }
}
