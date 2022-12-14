package com.microservice.currencyconversionservice.proxy;

import com.microservice.currencyconversionservice.controller.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{FROM}/to/{TO}")
    CurrencyConversion retrieveExchangeValue(@PathVariable("FROM") String from, @PathVariable("TO") String to);
}
