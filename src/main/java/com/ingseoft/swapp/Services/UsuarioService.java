package com.ingseoft.swapp.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Usuario;
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
    private UsuarioRepository repositorio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias
    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<Usuario> obtenerTodosLosUsuarios() {
        return this.repositorio.findAll();
    }

    // CU001
    // 1. Inicia Sesión
    // 2. Ingresa correo
    public Usuario agregarUsuario (Usuario nuevoUsuario) throws Exception {
        // 2. sistema revisa que no existe otro trocador con el mismo nombre
        List<Usuario> usuariosMismoCorreo = this.repositorio.findByCorreo(nuevoUsuario.getCorreo());

        List<Usuario> usuariosMismoCelular = this.repositorio.findByCelular(nuevoUsuario.getCelular());

        List<Usuario> usuariosMismoDocumento = this.repositorio.findByDocumentoIdentificacion(nuevoUsuario.getDocumentoIdentificacion());

        // 2. Verifica que no exista un correo similar
        if (!usuariosMismoCorreo.isEmpty()) {
            throw new Exception("Ya existe un usuario con el mismo correo.");
        } else if (!usuariosMismoCelular.isEmpty()) {
            throw new Exception("Ya existe un usuario con el mismo celular.");
        } else if (!usuariosMismoDocumento.isEmpty()) {
            throw new Exception("Ya existe un usuario con el mismo documento.");
        } else {
            // 3. sistema almacena el trocador
            return this.repositorio.save(nuevoUsuario);
        }

    }

}
