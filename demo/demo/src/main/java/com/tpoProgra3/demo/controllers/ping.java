package com.tpoProgra3.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController


public class ping {
    
    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }
}
