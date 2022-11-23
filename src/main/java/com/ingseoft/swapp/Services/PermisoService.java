package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearPermisoDto;
import com.ingseoft.swapp.Model.Permiso;
import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Repositories.PermisoRepository;
import com.ingseoft.swapp.Repositories.RolRepository;



@Component
public class PermisoService {

    // atributo
    private PermisoRepository repositorioPermiso;
    private RolRepository repositorioRol;


    public PermisoService(
        PermisoRepository repositorioPermiso,
        RolRepository repositorioRol
    ) {
        this.repositorioPermiso = repositorioPermiso;
        this.repositorioRol = repositorioRol;
    }

    // Casos de uso

    public Iterable<Permiso> obtenerTodosLosPermisos() {
        return this.repositorioPermiso.findAll();
    }


    public Permiso agregarPermiso (Permiso nuevoPermiso) throws Exception {
        return this.repositorioPermiso.save(nuevoPermiso);
    }

    public Permiso agregCrearPermisoDto (CrearPermisoDto nuevoPermiso) throws Exception {
        Permiso permiso = new Permiso(nuevoPermiso.getId(), nuevoPermiso.getNombre(), nuevoPermiso.getDescripcion(), new Rol());
        Optional<Rol> rol = repositorioRol.findById(nuevoPermiso.getRol_id());
        permiso.setRol(rol.get());
        return this.repositorioPermiso.save(permiso);
    }

    /* public Rol crearRolDto (CrearRolDto nuevoRol) throws Exception {
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
    } */
}