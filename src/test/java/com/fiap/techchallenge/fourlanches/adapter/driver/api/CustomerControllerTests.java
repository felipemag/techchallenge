package com.fiap.techchallenge.fourlanches.adapter.driver.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers.CustomerController;
import com.fiap.techchallenge.fourlanches.adapter.driver.api.controllersAdvisor.ApiErrorMessage;
import com.fiap.techchallenge.fourlanches.adapter.driver.api.controllersAdvisor.CustomerControllerAdvisor;
import com.fiap.techchallenge.fourlanches.application.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.application.exception.CustomerSaveException;
import com.fiap.techchallenge.fourlanches.application.dto.CustomerDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import com.fiap.techchallenge.fourlanches.domain.usecases.CustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mvc;

    @Mock
    private CustomerUseCase customerUseCase;

    @InjectMocks
    private CustomerController customerController;

    @Autowired
    private JacksonTester<Customer> jsonCustomer;

    @Autowired
    private JacksonTester<ApiErrorMessage> jsonApiErrorMessage;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new CustomerControllerAdvisor())
                .build();
    }

    @Test
    void givenDocument_whenCustomerNotFound_ThenError() throws Exception {
        var expectedErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, "customer not found");

        when(customerUseCase.getCustomerByDocument("00011122233")).thenThrow(CustomerNotFoundException.class);

        var response = mvc.perform(get("/customers/00011122233").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonApiErrorMessage.write(expectedErrorMessage).getJson());

    }

    @Test
    void givenDocument_whenCustomerIsFound_ThenReturnCustomer() throws Exception {
        var expectedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(customerUseCase.getCustomerByDocument("00011122233")).thenReturn(expectedCustomer);

        var response = mvc.perform(get("/customers/00011122233").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCustomer.write(expectedCustomer).getJson());

    }

    @Test
    void givenCustomerToBeSaved_whenSaveIsSuccessful_ThenReturnCustomer() throws Exception {
        var expectedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        var customerToBeSaved = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(customerUseCase.saveCustomer(customerToBeSaved)).thenReturn(expectedCustomer);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var body = ow.writeValueAsString(customerToBeSaved);

        var response = mvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCustomer.write(expectedCustomer).getJson());

    }

    @Test
    void givenCustomerToBeSaved_whenSaveFails_ThenError() throws Exception {
        var customerToBeSaved = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        var expectedErrorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "could not save customer");

        when(customerUseCase.saveCustomer(customerToBeSaved)).thenThrow(CustomerSaveException.class);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var body = ow.writeValueAsString(customerToBeSaved);

        var response = mvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonApiErrorMessage.write(expectedErrorMessage).getJson());

    }
}
