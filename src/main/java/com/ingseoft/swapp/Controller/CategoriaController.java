package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Services.CategoriaService;



@RestController
public class CategoriaController {

    // servicio
    @Autowired
    private CategoriaService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public CategoriaController(CategoriaService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST

    @GetMapping("/categorias")
    public Iterable<Categoria> leerTodosLasCategorias() {
        return this.servicio.obtenerTodosLasCategorias();

    }

    @PostMapping("/nueva-categoria")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String agregarPermisos(@RequestBody Categoria nuevaCategoria) throws Exception {
        Categoria nuevo = this.servicio.agregarCategoria(nuevaCategoria);
        return nuevo.getNombre();
    }

}
