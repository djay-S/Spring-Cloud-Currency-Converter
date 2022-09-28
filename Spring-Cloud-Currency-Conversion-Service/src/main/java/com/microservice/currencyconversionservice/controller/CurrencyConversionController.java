package com.microservice.currencyconversionservice.controller;

import com.microservice.currencyconversionservice.controller.model.CurrencyConversion;
import com.microservice.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{FROM}/to/{TO}/quantity/{QUANTITY}")
    public CurrencyConversion calculateConversion(@PathVariable("FROM") String from, @PathVariable("TO") String to,
            @PathVariable("QUANTITY") BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("FROM", from);
        uriVariables.put("TO", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().
                getForEntity("http://localhost:8000/currency-exchange/from/{FROM}/to/{TO}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + "rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{FROM}/to/{TO}/quantity/{QUANTITY}")
    public CurrencyConversion calculateConversionFeign(@PathVariable("FROM") String from, @PathVariable("TO") String to,
            @PathVariable("QUANTITY") BigDecimal quantity) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + " feign");
    }
}
