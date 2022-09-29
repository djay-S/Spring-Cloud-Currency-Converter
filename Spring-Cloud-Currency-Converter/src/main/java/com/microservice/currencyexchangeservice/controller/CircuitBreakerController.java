package com.microservice.currencyexchangeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    public String sampleApi() {
        return "Sample API";
    }
}
