package com.forgeMQ.forgeMQ.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api")
public class Controller {
    public String hello() {
        return "Hello, World!";
    }
}
