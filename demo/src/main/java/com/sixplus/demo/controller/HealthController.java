package com.sixplus.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/actuator/health")
public class HealthController {
    @GetMapping
    public String healthy(){
        return "ok";
    }
}
