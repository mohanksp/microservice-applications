package com.devtechnical.customerservice.controller;

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

    @GetMapping("/status/check")
    public String getStatus(){
        return "working on port : "+environment.getProperty("local.server.port");
    }

    @GetMapping("/{id}")
    public String getCustomers(@PathVariable String id){
        return "hello customer service "+id;
    }

}
