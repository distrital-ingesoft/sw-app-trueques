package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearElementoTruequeDto;
import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Usuario;
import com.ingseoft.swapp.Repositories.CategoriaRepository;
import com.ingseoft.swapp.Repositories.ElementoDeseadoRepository;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;
import com.ingseoft.swapp.Repositories.UsuarioRepository;



@Component
public class ElementoTruequeService {

    // atributo
    private ElementoTruequeRepository repositorioElementosTrueque;
    private UsuarioRepository repositorioUsuarios;
    private CategoriaRepository repositorioCategorias;
    private ElementoDeseadoRepository repositorioElementosDeseados;


    public ElementoTruequeService(
        ElementoTruequeRepository repositorioElementosTrueque,
        UsuarioRepository repositorioUsuarios,
        CategoriaRepository repositorioCategorias,
        ElementoDeseadoRepository repositorioElementosDeseados
    ) {
        this.repositorioElementosTrueque = repositorioElementosTrueque;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioCategorias = repositorioCategorias;
        this.repositorioElementosDeseados = repositorioElementosDeseados;
    }

    // Casos de uso
    public Iterable<ElementoTrueque> obtenerTodosLosElementoTrueque() {
        return this.repositorioElementosTrueque.findAll();
    }

    public ElementoTrueque agregarElementoTrueque (CrearElementoTruequeDto nuevoElementoTrueque) throws Exception {
        ElementoTrueque parametro = new ElementoTrueque(
            nuevoElementoTrueque.getId(),
            nuevoElementoTrueque.getNombre(),
            nuevoElementoTrueque.getDescripcion(),
            nuevoElementoTrueque.getEnlaceImagen(),
            nuevoElementoTrueque.getPrecio(),
            nuevoElementoTrueque.getDisponible(),
            nuevoElementoTrueque.getEstadoElemento(),
            new Usuario(),
            new Categoria(),
            new ArrayList<>()
        );

        Optional<Usuario> usuario = this.repositorioUsuarios.findById(nuevoElementoTrueque.getUsuario_id());

        if(usuario.isEmpty()) {
            throw new Exception("No existe el id de usuario ingresado.");
        } else {
            parametro.setUsuario(usuario.get());
        }

        Optional<Categoria> categoria = this.repositorioCategorias.findById(nuevoElementoTrueque.getCategoria_id());

        if(categoria.isEmpty()) {
            throw new Exception("No existe el id de categoria ingresado.");
        } else {
            parametro.setCategoria(categoria.get());
        }

        /* for(Integer idElementoDeseado: nuevoElementoTrueque.getElementosDeseados()) {
            Optional<ElementoDeseado> elementoDeseado = repositorioElementosDeseados.findById(idElementoDeseado);
            if(elementoDeseado.isPresent()) {
                parametro.getElementosDeseados().add(elementoDeseado.get());
            } else {
                throw new Exception("El elemento deseado no existe.");
            }
        } */

        return this.repositorioElementosTrueque.save(parametro);
    }
}
