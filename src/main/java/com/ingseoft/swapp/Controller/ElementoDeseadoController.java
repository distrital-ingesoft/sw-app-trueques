package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Dto.CrearElementoDeseadoDto;
import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Services.ElementoDeseadoService;



@RestController
public class ElementoDeseadoController {

    // servicio
    @Autowired
    private ElementoDeseadoService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public ElementoDeseadoController(ElementoDeseadoService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST

    @GetMapping("/elementos-deseados")
    public Iterable<ElementoDeseado> leerTodosLosElementoDeseados() {
        return this.servicio.obtenerTodosLosElementoDeseados();
    }

    @PostMapping("/nuevo-elemento-deseado")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String agregarElementoDeseado(@RequestBody CrearElementoDeseadoDto nuevoElementoDeseado) throws Exception {
        ElementoDeseado nuevo = this.servicio.agregarElementoDeseado(nuevoElementoDeseado);
        return nuevo.getNombre();
    }

}
