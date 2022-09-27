package com.microservice.currencyconversionservice.controller;

import com.microservice.currencyconversionservice.controller.model.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{FROM}/to/{TO}/quantity/{QUANTITY}")
    public CurrencyConversion calculateConversion(@PathVariable("FROM") String from, @PathVariable("TO") String to,
            @PathVariable("QUANTITY") BigDecimal quantity) {
        return new CurrencyConversion(10001L, from, to, quantity, BigDecimal.ONE, BigDecimal.ONE, "");
    }
}
