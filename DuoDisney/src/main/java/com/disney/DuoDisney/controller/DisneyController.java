package com.disney.DuoDisney.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @author aduo

@Controller
@RequestMapping("/") // localhost:8080/
public class DisneyController {

    @GetMapping("/") // localhost:8080/
    public String index() {
        return "index.html";
    }
    @GetMapping("/login") // localhost:8080/login
    public String login(@RequestParam (required=false)String error, @RequestParam(required = false) String logout, ModelMap model) {
        if(error!= null){
        model.put("error", "Incorrect username or password");
        }
        if(logout != null){
            model.put("logout","You have successfully logged out");
        }
        return "login.html";
    }
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio") // localhost:8080/inicio
    public String inicio() {
        return "inicio.html";
    }
}
