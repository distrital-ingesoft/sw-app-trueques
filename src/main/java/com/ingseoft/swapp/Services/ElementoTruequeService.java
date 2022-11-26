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
        // 1. Ingresa nombre del elemento.
        // 2. Ingresa enlace de imagen de elemento.
        // 3. Ingresa descripción.
        // 4. Se almacena inicialmente como disponible (disponible = true).
        // 5. Ingresa el precio del elemento.
        // 6. Se almacena inicialmente con estado ACTIVO.
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

        // 7. Selecciona categoría del elemento.
        // 8. Valida que exista la categoría.
        Optional<Categoria> categoria = this.repositorioCategorias.findById(nuevoElementoTrueque.getCategoria_id());

        if(categoria.isEmpty()) {
            throw new Exception("No existe el id de categoria ingresado.");
        } else {
            parametro.setCategoria(categoria.get());
        }

        // 9. Asocia los elementos deseados que quiere como trueque del elemento registrado.

        // 10. Valida que existan los elementos deseados seleccionados.

        // 11. Valida que el usuario no supere los 3 elementos deseados.

        // 12. Ingresa el id del usuario que agregó el elemento.
        // 13. Valida que exista el usuario.
        Optional<Usuario> usuario = this.repositorioUsuarios.findById(nuevoElementoTrueque.getUsuario_id());

        if(usuario.isEmpty()) {
            throw new Exception("No existe el id de usuario ingresado.");
        } else {
            parametro.setUsuario(usuario.get());
        }

        // 14. Almacena el elemento en la base de datos.
        return this.repositorioElementosTrueque.save(parametro);
    }
}
