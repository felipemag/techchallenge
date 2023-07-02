package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String document;

    public Customer getCustomerEntity() {

        return Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .document(document)
                .build();
    }
}
