package com.simplilearn.spring.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudGatewayConfiguration {

    /*
     * Custom Routes
     */
    @Bean
    RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes().route(p -> p.path("/sleep/**")
                                       .uri("lb://sleep-service"))
                               .route(p -> p.path("/users/**")
                                       .uri("lb://users-service"))
                               .route(p -> p.path("/hello/**")
                                       .filters(f -> f.rewritePath(
                                                     "/hello/(?<segment>.*)",
                                                     "/users/${segment}"))
                                       .uri("lb://users-service"))
                               .build();
    }

}
