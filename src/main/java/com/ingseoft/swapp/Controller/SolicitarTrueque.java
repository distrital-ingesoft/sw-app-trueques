package com.ingseoft.swapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitarTrueque {
    @GetMapping("/")
    public String getMessage(){
        return "Hello World";
    }
}
