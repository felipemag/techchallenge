package com.fiap.techchallenge.fourlanches.application.dto;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.CustomerJpaEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @Size(min = 11, max = 11, message = "invalid document size, it should have 11 characters")
    private String document;

    public CustomerJpaEntity toEntity() {
        return CustomerJpaEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .document(document)
                .build();
    }
}

