package com.disney.DuoDisney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @author aduo

@Controller
@RequestMapping("/") // localhost:8080/
public class DisneyController {

    @GetMapping("/") // localhost:8080/
    public String index() {
        return "index.html";
    }
     @GetMapping("/home") // localhost:8080/home
    public String home() {
        return "index.html";
    }
}
