package com.devtechnical.accountmanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private Environment environment;

    @GetMapping("/status/check")
    public String getStatus(){
        return "working on port : "+environment.getProperty("server.port");
    }
}
