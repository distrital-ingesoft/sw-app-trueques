package com.ingseoft.swapp.Services;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Permiso;
import com.ingseoft.swapp.Repositories.PermisoRepository;



@Component
public class PermisoService {

    // atributo
    private PermisoRepository repositorio;


    public PermisoService(PermisoRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<Permiso> obtenerTodosLosPermisos() {
        return this.repositorio.findAll();
    }
    

    public Permiso agregarPermiso (Permiso nuevoPermiso) throws Exception {

            return this.repositorio.save(nuevoPermiso);

    }
}
