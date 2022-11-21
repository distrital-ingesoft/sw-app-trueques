package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Services.RolService;



@RestController
public class RolController {

    // servicio
    @Autowired
    private RolService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public RolController(RolService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST

    @GetMapping("/Rol")
    public Iterable<Rol> leerTodosLosRoles() {
        return this.servicio.obtenerTodosLosRoles();

    }

    @PostMapping("/nuevoRol")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String agregarPermisos(@RequestBody Rol nuevoRol) throws Exception {
        Rol nuevo = this.servicio.agregarRol(nuevoRol);
        return nuevo.getNombre();
    }

}
