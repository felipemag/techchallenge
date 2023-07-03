package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.aggregates.FakeCheckoutAggregate;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("fake-checkout")
public class FakeCheckoutController {
    private FakeCheckoutAggregate fakeCheckoutAggregate;

    @PostMapping("")
    @ApiResponse(responseCode = "200")
    public boolean doFakeCheckout(@RequestParam long orderId, @RequestParam BigDecimal totalAmount) {
        return fakeCheckoutAggregate.doFakeCheckout(orderId, totalAmount);
    }

}
