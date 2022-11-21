package com.ingseoft.swapp.Services;

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
    
    // CU1
    // 1. actor ingresa datos del trocador
    // 2. sistema revisa que no existe otro trocador con el mismo nombre
    // 3. sistema almacena el trocador
    public Usuario agregarUsuario (Usuario nuevoUsuario) throws Exception {

        // 2. sistema revisa que no existe otro trocador con el mismo nombre
        if (nuevoUsuario.getCorreo().equals("ejemplo")) {
            throw new Exception("Correo ya existe!!");
        
        } else {

            // 3. sistema almacena el trocador
            return this.repositorio.save(nuevoUsuario);
        }

    }

}
