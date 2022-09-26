package com.microservice.currencyexchangeservice.controller;

import com.microservice.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{FROM}/to/{TO}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("FROM") String from, @PathVariable("TO") String to) {

        return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
    }
}
