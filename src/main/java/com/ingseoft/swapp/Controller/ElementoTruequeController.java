package com.ingseoft.swapp.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/ElementosTrueque")
    public Iterable<ElementoTrueque> leerTodosLosElementoTrueque() {
        return this.servicio.obtenerTodosLosElementoTrueque();

    }



    @PostMapping("/nuevoElementoTrueque")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String agregarElementoTrueque(@RequestBody ElementoTrueque nuevoElementoTrueque) throws Exception {
        ElementoTrueque nuevo = this.servicio.agregarElementoTrueque(nuevoElementoTrueque);
        return nuevo.getNombre();
    }

    @PutMapping("/ElementoTruequeEstado/Id")
    public Boolean actualizarEstadoElementoTrueque(@RequestParam Integer Id)  {
        Boolean Estado  = this.servicio.actualizarEstadoElementoTrueque(Id);
        return Estado;
    }


    @GetMapping("/ElementosTrueque/categoriaId")
    public Iterable<ElementoTrueque> leerElementoTruequePorCategoria(@RequestParam Integer categoriaId) {
        return this.servicio.ObtenerElementoTruequePorCategoria(categoriaId);
    }

    @GetMapping("/ElementosTrueque/Id")
    public Optional<ElementoTrueque> leerElementoTruequePorid(@RequestParam Integer Id) {
        return this.servicio.ObtenerElementoTrueque(Id);
    }


    @DeleteMapping("/ElementosTrueque/Id")
    public String eliminarElementoTruequeById(@RequestParam Integer Id) {
        return this.servicio.eliminarElementoTruequeById(Id);
    }
}
