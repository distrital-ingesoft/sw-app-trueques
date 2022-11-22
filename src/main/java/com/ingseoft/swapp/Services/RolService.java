package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearRolDto;
import com.ingseoft.swapp.Model.Permiso;
import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Repositories.PermisoRepository;
import com.ingseoft.swapp.Repositories.RolRepository;



@Component
public class RolService {

    // atributo
    private RolRepository repositorioRoles;
    private PermisoRepository repositorioPermisos;


    public RolService(RolRepository repositorioRoles, PermisoRepository repositorioPermisos) {
        this.repositorioRoles = repositorioRoles;
        this.repositorioPermisos = repositorioPermisos;
    }

    // Casos de uso
    public Iterable<Rol> obtenerTodosLosRoles() {
        return this.repositorioRoles.findAll();
    }

    public Rol agregarRol (Rol nuevoRol) throws Exception {
        return this.repositorioRoles.save(nuevoRol);
    }

    public Rol crearRolDto (CrearRolDto nuevoRol) throws Exception {
        Rol parametro = new Rol(nuevoRol.getId(), nuevoRol.getNombre(), nuevoRol.getDescripcion(), nuevoRol.getActivo(), new ArrayList<>());
        for(Integer idPermiso: nuevoRol.getPermisos()) {
            Optional<Permiso> permiso = repositorioPermisos.findById(idPermiso);
            if(permiso.isPresent()) {
                parametro.getPermisos().add(permiso.get());
            } else {
                throw new Exception("Uno de los permisos no existe.");
            }
        }
        return this.repositorioRoles.save(parametro);
    }
}
