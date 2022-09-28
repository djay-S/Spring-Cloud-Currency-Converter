package com.microservice.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        p -> p.path("/get")
                                .filters(f -> f
                                        .addRequestHeader("My-Header", "My Header Value")
                                        .addRequestParameter("My-Request-Parameter", "My-Request-Parameter-Value"))
                                .uri("http://httpbin.org:80")
                )
                .route(
                                //All the requests of the given path
                        p -> p.path("/currency-exchange/**")
                                //Talk to the Eureka server of the given server name and load balance the instances which are returned
                                .uri("lb://currency-exchange")
                )
                .route(
                        p -> p.path("/currency-conversion/**")
                                .uri("lb://currency-conversion")
                )
                .route(
                        p -> p.path("/currency-conversion-feign/**")
                                .uri("lb://currency-conversion")
                )
                .route(
                        p -> p.path("/currency-conversion-new/**")
                                .filters(f -> f
                                        .rewritePath(
                                                "/currency-conversion-new/(?<segment>.*)",
                                                "/currency-conversion-feign/${segment}"))
                                .uri("lb://currency-conversion")
                )
                .build();
    }
}
