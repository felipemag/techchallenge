package com.fiap.techchallenge.fourlanches.adapter.driver.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.CustomerVO;
import com.fiap.techchallenge.fourlanches.domain.aggregates.CustomerAggregate;
import com.fiap.techchallenge.fourlanches.domain.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@Import(CustomerControllerAdvisor.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerAggregate customerAggregate;

    @Test
    void givenDocument_whenCustomerNotFound_ThenReturnCustomer() throws Exception {
        var expectedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(customerAggregate.getCustomerByDocument("00011122233")).thenReturn(expectedCustomer);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var expectedJson = ow.writeValueAsString(expectedCustomer)
                .replace("\n", "")
                .replace("\r", "")
                .replaceAll("\\s", "");

        mvc.perform(get("/customers/00011122233")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result.getResponse()
                        .getContentAsString(), expectedJson));

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

        var customerToBeSaved = CustomerVO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .document("00011122233")
                .build();

        when(customerAggregate.saveCustomer(customerToBeSaved)).thenReturn(expectedCustomer);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        var expectedJson = ow.writeValueAsString(expectedCustomer)
                .replace("\n", "")
                .replace("\r", "")
                .replaceAll("\\s", "");

        var body = ow.writeValueAsString(customerToBeSaved);

        mvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result.getResponse()
                        .getContentAsString(), expectedJson));

    }
}
