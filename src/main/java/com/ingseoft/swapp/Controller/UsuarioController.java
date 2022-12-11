package com.ingseoft.swapp.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Usuario;
import com.ingseoft.swapp.Services.UsuarioService;

// REST - arquitectura basada en recursos
// no está basada en RPC -- en invocar procedimientos
// todo se hace a través de unos CRUD sobre unos recursos

// Usamos los comandos de HTTP

// Recurso: /trocadores
//  GET /trocadores         - retorna una lista de trocadores
//  POST /trocadores        - agregar un nuevo trocador
//  GET /trocadores/{id}    - retorna un trocador
//  PUT /trocadores/{id}    - modifica un trocador
//  DELE /trocadores/{id}   - borrar un trocador
@CrossOrigin("*")
@RestController
public class UsuarioController {

    // servicio
    @Autowired
    private UsuarioService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST

    @GetMapping("/Usuarios")
    public Iterable<Usuario> leerTodosLosUsuario() {
        return this.servicio.obtenerTodosLosUsuarios();

    }

    @PostMapping("/Log")
    public Usuario  ValidarContrasena(@RequestBody Usuario validarUsuario){
         Usuario usuario = this.servicio.iniciarSesion(validarUsuario);
         return usuario;

    }

    @PostMapping("/nuevoUsuario")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario agregarUsuario(@RequestBody Usuario nuevoUsuario) {
        //Usuario nuevo = this.servicio.agregarUsuario(nuevoUsuario);
        Usuario creado = this.servicio.registrarUsuario(nuevoUsuario);
        return creado;
    }

    @PostMapping("/nuevoAdmin")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario agregarAdmin(@RequestBody Usuario nuevoUsuario) {
        //Usuario nuevo = this.servicio.agregarUsuario(nuevoUsuario);
        Usuario creado = this.servicio.registrarAdmin(nuevoUsuario);
        return creado;
    }

    @GetMapping("/ElementosUsuarios/{id}")
    public List<ElementoTrueque> leerTodosLosElementosUsuario(@PathVariable Integer id) {
        Usuario usuario = this.servicio.obtenerUsuario(id);
        return usuario.getElementosTrueque();
    }
}
