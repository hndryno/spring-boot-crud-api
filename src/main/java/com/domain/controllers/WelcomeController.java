package com.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/api/welcome")
public class WelcomeController {

    // @GetMapping //akan menghandle request yang datang, untuk method apinya
    // public String welcome(){
    //     return "welcome to spring boot rest api";
    // }

    @GetMapping({"/halo"}) //akan menghandle request yang datang, untuk method apinya
    public String halo(){
        return "Welcome";
    }
}

