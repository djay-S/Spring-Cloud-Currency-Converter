package com.microservice.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "default") annotation retries the failing api call.
//    If name is 'default' it retries for 3 times, custom name can be specified at the application.properties file
//    fallbackMethod tells which method to be invoked when the request fails
//    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")

//    https://resilience4j.readme.io/docs/circuitbreaker
    @CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String sampleApi() {
        logger.info("Sample Api Called. Timestamp: {}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8081/this-url-fails", String.class);
        return forEntity.getBody();
    }

    public String fallbackMethod(Exception e) {
        return "Fallback Method: \n" + e;
    }
}
