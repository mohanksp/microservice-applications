package com.devtechnical.customerservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private Environment environment;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/status/check")
    public String getStatus(){
        logger.info("called getStatus() method");
        return "working on port : "+environment.getProperty("local.server.port");
    }

    @GetMapping("/read")
    public String getReadValue(){
        logger.info("called getReadValue() method");
        return environment.getProperty("read.value");
    }

    @GetMapping("/{id}")
    public String getCustomers(@PathVariable String id) {
        logger.info("called getCustomers() method with "+id+" parameter");
        return "hello customer service "+id;
    }

}
