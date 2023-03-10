/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.currency.exchange.service;

import io.github.resilience4j.bulkhead.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.*;
import io.github.resilience4j.ratelimiter.annotation.*;
import io.github.resilience4j.retry.annotation.*;
import org.hibernate.loader.entity.plan.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

/**
 *
 * @author PC
 */
@RestController
public class CircuitBreakerController {
    
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
            
    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String sampleApi(){
        logger.info("Sample Api call received");
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//        return forEntity.getBody();
        return "sample2"; 
    }
    
    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }
}
