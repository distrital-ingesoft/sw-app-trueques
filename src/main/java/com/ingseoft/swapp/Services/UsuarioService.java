package com.ingseoft.swapp.Services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UsuarioRepository repositorio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias
    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    // ------------Casos de uso------------------------------------------
    
    // CU003 Registrar Usuario
    // 1. actor ingresa datos del trocador
    // 2. sistema revisa que no existe otro trocador con el mismo correo
    // 3. sistema almacena el trocador
    public Usuario agregarUsuario (Usuario nuevoUsuario)  {

        // 2. sistema revisa que no existe otro trocador con el mismo correo
        if (this.repositorio.existsByCorreo(nuevoUsuario.getCorreo())) {
            return null;
        } else {

            // 3. sistema almacena el trocador
            this.repositorio.save(nuevoUsuario);
            return nuevoUsuario;
        }

    }

    // CU000 Traer elementos trueque por usuario
    public Usuario ObtenerUsuario (Integer id){
        Optional<Usuario> usuario = this.repositorio.findById(id);
        Usuario usr = usuario.get();
        return usr;
    }

    // CU00 Login validar clave
    public Usuario validarUsuario(Usuario validarUsuario) {
            // Sistema revisa si existe correo
            if (this.repositorio.existsByCorreo(validarUsuario.getCorreo())) {
                // Validar Clave
                Usuario usuario = this.repositorio.findByCorreo(validarUsuario.getCorreo()).get();
                if(usuario.getContrasena().equals(validarUsuario.getContrasena())){
                    //Contraseña Correcta
                    usuario.setContrasena("");
                    return usuario;
                }else {
                    //Contraseña Incorrecta
                    return null;
                }
            } else {
                // Correo no existe
                return null;
            }


    }
    //-----------------Otros--------------------------------------------

    public Iterable<Usuario> obtenerTodosLosUsuarios() {
        return this.repositorio.findAll();
    }





}
