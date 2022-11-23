package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Dto.CrearPermisoDto;
import com.ingseoft.swapp.Model.Permiso;
import com.ingseoft.swapp.Services.PermisoService;



@RestController
public class PermisoController {

    // servicio
    @Autowired
    private PermisoService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public PermisoController(PermisoService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST

    @GetMapping("/Permisos")
    public Iterable<Permiso> leerTodosLosPermisos() {
        return this.servicio.obtenerTodosLosPermisos();
    }

    @PostMapping("/nuevoPermiso")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String agregarPermisos(@RequestBody CrearPermisoDto nuevoPermiso) throws Exception {
        Permiso nuevo = this.servicio.agregCrearPermisoDto(nuevoPermiso);
        return nuevo.getNombre();
    }



}
