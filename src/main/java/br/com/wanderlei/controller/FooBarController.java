package br.com.wanderlei.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Tag (name="Foo-Bar Endpoint")
@RestController
@RequestMapping ("book-service")
//@Retry (name="default")
//@Retry (name="foo-bar", fallbackMethod = "fallBackMethod")
//@CircuitBreaker (name="default", fallbackMethod = "fallBackMethod")
@RateLimiter (name="default")

public class FooBarController {

    private Logger logger = LoggerFactory.getLogger (FooBarController.class);
    @GetMapping("/foo-bar")
    public String fooBar(){
        logger.info ("Request to foo-bar is Received");
/*
        var response = new RestTemplate()
                .getForEntity ("http://localhost:8082/foo-bar", String.class);
        return response.getBody ();
*/
        return "foo-bar";
    }

    public String fallbackMethod(Exception ex) {
        return "fallbackMethod foo-bar";
    }

}
