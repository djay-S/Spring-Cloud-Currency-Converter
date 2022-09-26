package com.microservice.currencyexchangeservice.controller;

import com.microservice.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{FROM}/to/{TO}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("FROM") String from, @PathVariable("TO") String to) {

        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
