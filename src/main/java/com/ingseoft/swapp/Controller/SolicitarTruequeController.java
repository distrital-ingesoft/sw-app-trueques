package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Service.ServicioTrueque;

@RestController
public class SolicitarTruequeController {

    @Autowired
    private ServicioTrueque service;

    public SolicitarTruequeController(ServicioTrueque service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getMessage(){
        return "Hello World";
    }

    @GetMapping("/trueques")
    public Iterable<Trueque> readAllBicycles () {
        return this.service.getAllTrueques();
    }

    /* @PostMapping("/trueques")
    @ResponseStatus(code= HttpStatus.CREATED)
    public String addBicycle ( @RequestBody Trueque request ) throws Exception{
        Trueque newTrueque = this.service.addTrueque(request);
        return newTrueque.getId().toString();
    } */
}
