package com.fiap.techchallenge.fourlanches.domain.valueobjects;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.CustomerJpaEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CustomerVO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
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
