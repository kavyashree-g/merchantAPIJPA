package com.au.merchant.merchantAPIJPA.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class MerchantHealthCheckController {

    @GetMapping
    public String getHealthCheck(){
        return "App is Up";
    }
}
