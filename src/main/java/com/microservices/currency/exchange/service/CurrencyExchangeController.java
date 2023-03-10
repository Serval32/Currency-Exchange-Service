/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.currency.exchange.service;

import java.math.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
public class CurrencyExchangeController {
    
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    
    @Autowired
    private Environment environment;
    
    @Autowired
    CurrencyExchangeRepository repository;
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to){
        
        logger.info("retrieveExchangeValue called with {} to {}",from, to);
        
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for "+ from + " to " + to);
        }
        
        String port = environment.getProperty("local.server.port");
        
        currencyExchange.setEnvironment(port);
        
        return currencyExchange;
    }

}
