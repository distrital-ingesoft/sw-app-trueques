package com.ingseoft.swapp.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Repositories.PermisoRepository;
import com.ingseoft.swapp.Repositories.RolRepository;



@Component
public class RolService {

    // atributo
    @Autowired
    private RolRepository repositorioRoles;


    public RolService(RolRepository repositorioRoles, PermisoRepository repositorioPermisos) {
        this.repositorioRoles = repositorioRoles;
    }

    // Casos de uso
    public Iterable<Rol> obtenerTodosLosRoles() {
        return this.repositorioRoles.findAll();
    }

    public Rol agregarRol (Rol nuevoRol) throws Exception {
        return this.repositorioRoles.save(nuevoRol);
    }
}
