package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Dto.CrearElementoTruequeDto;
import com.ingseoft.swapp.Dto.EliminarElementoTruequeDto;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Services.ElementoTruequeService;



@RestController
public class ElementoTruequeController {

    // servicio
    @Autowired
    private ElementoTruequeService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public ElementoTruequeController(ElementoTruequeService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST

    @GetMapping("/elementos-trueque")
    public Iterable<ElementoTrueque> leerTodosLosElementoTrueque() {
        return this.servicio.obtenerTodosLosElementoTrueque();
    }

    @PostMapping("/nuevo-elemento-trueque")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String registrarElemento(@RequestBody CrearElementoTruequeDto nuevoElementoTrueque) throws Exception {
        ElementoTrueque nuevo = this.servicio.registrarElemento(nuevoElementoTrueque);
        return nuevo.getNombre();
    }

    @PostMapping("/eliminar-elemento-trueque")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String eliminarElemento(@RequestBody EliminarElementoTruequeDto elementoTrueque) throws Exception {
        String result = this.servicio.eliminarElemento(elementoTrueque);
        return result;
    }
}
