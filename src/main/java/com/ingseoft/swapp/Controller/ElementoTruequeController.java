package com.ingseoft.swapp.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Services.ElementoTruequeService;


@CrossOrigin("*")
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
    public ElementoTrueque agregarElementoTrueque(@RequestBody ElementoTrueque nuevoElementoTrueque) throws Exception {
        ElementoTrueque nuevo = this.servicio.registrarElementoTrueque(nuevoElementoTrueque);
        return nuevo;
    }

    @PutMapping("/ElementoTruequeEstado/Id")
    public Boolean actualizarEstadoElementoTrueque(@RequestParam Integer Id)  {
        Boolean Estado  = this.servicio.actualizarEstadoElementoTrueque(Id);
        return Estado;
    }


    @GetMapping("/ElementosTrueque/categoria/{categoriaId}")
    public Iterable<ElementoTrueque> leerElementoTruequePorCategoria(@PathVariable Integer categoriaId) {
        return this.servicio.ObtenerElementoTruequePorCategoria(categoriaId);
    }

    @GetMapping("/ElementosTrueque/{Id}")
    public Optional<ElementoTrueque> leerElementoTruequePorid(@PathVariable Integer Id) {
        return this.servicio.ObtenerElementoTrueque(Id);
    }


    @DeleteMapping("/ElementosTrueque/{Id}")
    public String eliminarElementoTruequeById(@PathVariable Integer Id) {
        return this.servicio.eliminarElementoTruequeById(Id);
    }
}
