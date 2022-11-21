package com.ingseoft.swapp.Services;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Repositories.RolRepository;



@Component
public class RolService {

    // atributo
    private RolRepository repositorio;


    public RolService(RolRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<Rol> obtenerTodosLosRoles() {
        return this.repositorio.findAll();
    }
    

    public Rol agregarRol (Rol nuevoRol) throws Exception {

            return this.repositorio.save(nuevoRol);

    }
}
