package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaController {

    @PostMapping("/greet")
    public String greet(@RequestBody Greeting greeting) {
        return "Hello, " + greeting.getName() + "!!";
    }
}

class Greeting {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}