package com.microservice.currencyexchangeservice.controller;

import com.microservice.currencyexchangeservice.dao.CurrencyExchangeRepository;
import com.microservice.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{FROM}/to/{TO}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("FROM") String from, @PathVariable("TO") String to) {

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
