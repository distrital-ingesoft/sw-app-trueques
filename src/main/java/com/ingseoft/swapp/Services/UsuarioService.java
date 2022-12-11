package com.ingseoft.swapp.Services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Rol;
import com.ingseoft.swapp.Model.Usuario;
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
    @Autowired
    private UsuarioRepository repositorio;

    // atributo
    @Autowired
    private RolRepository rolRepository;
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
    public Usuario registrarUsuario (Usuario nuevoUsuario) throws Exception {
        if (this.repositorio.existsByCorreo(nuevoUsuario.getCorreo())) {
            throw new Exception("Ya existe un usuario registrado con los mismos datos");
        }

        Optional<Rol> rol = this.rolRepository.findById(1);

        if (rol.isEmpty()) {
            throw new Exception("No existe un rol registrado");
        }

        this.repositorio.save(nuevoUsuario);

        return nuevoUsuario;
    }

    // CU000 Traer elementos trueque por usuario
    public Usuario obtenerUsuario (Integer id){
        Optional<Usuario> usuario = this.repositorio.findById(id);
        Usuario usr = usuario.get();
        return usr;
    }

    // Iniciar Sesion
    public Usuario iniciarSesion(Usuario validarUsuario) throws Exception {
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
                    throw new Exception("La combinación de correo y la contraseña no coinciden.");
                }
            } else {
                // Correo no existe
                throw new Exception("La combinación de correo y la contraseña no coinciden.");
            }


    }
    //-----------------Otros--------------------------------------------

    public Iterable<Usuario> obtenerTodosLosUsuarios() {
        return this.repositorio.findAll();
    }





}
