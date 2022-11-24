package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearUsuarioDto;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Model.Usuario;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;
import com.ingseoft.swapp.Repositories.RolRepository;
import com.ingseoft.swapp.Repositories.UsuarioRepository;

// servicio independiente de la tecnología de invocación
// se puede invocar con JMS (mensajería), con REST, en una pantalla

// RestController       -- recibe la solicitud REST
//      |
//      v
// Service              -- lógica de negocio
//      |
//      v
// Repository, Entity   -- entidades de negocio

@Component
public class UsuarioService {

    // atributo
    private UsuarioRepository repositorioUsuarios;
    private RolRepository repositorioRoles;
    private ElementoTruequeRepository repositorioElementosTrueque;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias
    public UsuarioService(
        UsuarioRepository repositorioUsuarios,
        RolRepository repositorioRoles,
        ElementoTruequeRepository repositorioElementosTrueque
    ) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioRoles = repositorioRoles;
        this.repositorioElementosTrueque = repositorioElementosTrueque;
    }

    // Casos de uso

    public Iterable<Usuario> obtenerTodosLosUsuarios() {
        return this.repositorioUsuarios.findAll();
    }

    // CU001
    // 1. Inicia Sesión
    // 2. Ingresa correo
    public Usuario agregarUsuario (CrearUsuarioDto nuevoUsuario) throws Exception {
        // 2. sistema revisa que no existe otro trocador con el mismo nombre
        List<Usuario> usuariosMismoCorreo = this.repositorioUsuarios.findByCorreo(nuevoUsuario.getCorreo());

        List<Usuario> usuariosMismoCelular = this.repositorioUsuarios.findByCelular(nuevoUsuario.getCelular());

        List<Usuario> usuariosMismoDocumento = this.repositorioUsuarios.findByDocumentoIdentificacion(nuevoUsuario.getDocumentoIdentificacion());

        Usuario parametro = new Usuario(
            nuevoUsuario.getId(),
            nuevoUsuario.getNombreCompleto(),
            nuevoUsuario.getDocumentoIdentificacion(),
            nuevoUsuario.getCorreo(),
            nuevoUsuario.getCelular(),
            nuevoUsuario.getCiudad(),
            nuevoUsuario.getContrasenia(),
            nuevoUsuario.getActivo(),
            new Rol(),
            new ArrayList<ElementoTrueque>()
        );

        Optional<Rol> rol = this.repositorioRoles.findById(nuevoUsuario.getRol_id());
        if(rol.isEmpty()) {
            throw new Exception("No existe el id de rol ingresado.");
        } else {
            parametro.setRol(rol.get());
        }

        /* for(Integer idElementoTrueque: nuevoUsuario.getElementos_trueque()) {
            Optional<ElementoTrueque> elementoTrueque = repositorioElementosTrueque.findById(idElementoTrueque);
            if(elementoTrueque.isPresent()) {
                parametro.getElementosTrueque().add(elementoTrueque.get());
            } else {
                throw new Exception("El elemento para trueque no existe.");
            }
        } */

        // 2. Verifica que no exista un correo similar
        if (!usuariosMismoCorreo.isEmpty()) {
            throw new Exception("Ya existe un usuario con el mismo correo.");
        } else if (!usuariosMismoCelular.isEmpty()) {
            throw new Exception("Ya existe un usuario con el mismo celular.");
        } else if (!usuariosMismoDocumento.isEmpty()) {
            throw new Exception("Ya existe un usuario con el mismo documento.");
        } else {
            // 3. sistema almacena el trocador
            return this.repositorioUsuarios.save(parametro);
        }

    }

}
