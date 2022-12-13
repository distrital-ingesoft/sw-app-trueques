package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Usuario;
import com.ingseoft.swapp.Repositories.CategoriaRepository;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;
import com.ingseoft.swapp.Repositories.UsuarioRepository;



@Component
public class ElementoTruequeService {

    // atributo
    @Autowired
    private ElementoTruequeRepository repositorioElementoTrueque;

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private CategoriaRepository repositorioCategoria;


    public ElementoTruequeService(ElementoTruequeRepository repositorioElementoTrueque) {
        this.repositorioElementoTrueque = repositorioElementoTrueque;
    }

    // Casos de uso


    public Iterable<ElementoTrueque> obtenerTodosLosElementoTrueque() {
        return this.repositorioElementoTrueque.findAll();
    }

    // Registrar nuevo Elemento
    public ElementoTrueque registrarElementoTrueque (ElementoTrueque nuevoElementoTrueque) throws Exception {
        Optional<Usuario> usuarioSolicitado =  this.repositorioUsuario.findById(nuevoElementoTrueque.getUsuario().getId());

        if(usuarioSolicitado.isEmpty()) {
            throw new Exception("No existe usuario registrado");
        }

        Optional<Categoria> categoria = this.repositorioCategoria.findById(nuevoElementoTrueque.getCategoria().getId());

        if(categoria.isEmpty()) {
            throw new Exception("No existe la categoría ingresada");
        }

        ElementoTrueque elemento = nuevoElementoTrueque;
        elemento.setDisponible(true);
        return this.repositorioElementoTrueque.save(elemento);
    }

    public  Optional<ElementoTrueque> ObtenerElementoTrueque (Integer id){
        return this.repositorioElementoTrueque.findById(id);
    }


    public Iterable<ElementoTrueque> ObtenerElementoTruequePorCategoria (Integer id){
        Categoria categoria = new Categoria();
        categoria.setId(id);
        return this.repositorioElementoTrueque.findByCategoria(categoria);
    }

    public Boolean actualizarEstadoElementoTrueque(Integer id) {
        ElementoTrueque elementoTrueque =  ObtenerElementoTrueque(id).get();
        elementoTrueque.setDisponible(!elementoTrueque.getDisponible());
        this.repositorioElementoTrueque.save(elementoTrueque);

        return elementoTrueque.getDisponible();
    }

    // Eliminar Elemento
    public String eliminarElementoTruequeById(Integer id) throws Exception {
        Optional<ElementoTrueque> elemento = this.repositorioElementoTrueque.findById(id);

        if(elemento.isEmpty()) {
            throw new Exception("No existe el elemento registrado");
        }

        Optional<Usuario> usuario = this.repositorioUsuario.findById(elemento.get().getUsuario().getId());

        if(usuario.isEmpty()) {
            throw new Exception("No existe el usuario registrado");
        }

        this.repositorioElementoTrueque.deleteById(id);
        return "Elemento Eliminado";
    }
}
