package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World! Mani...";
    }

    @GetMapping("/hello1")
    public String hello1() {
        return "Hello, World! Karthik...";
    }
}
