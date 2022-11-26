package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearElementoTruequeDto;
import com.ingseoft.swapp.Dto.EliminarElementoTruequeDto;
import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Usuario;
import com.ingseoft.swapp.Repositories.CategoriaRepository;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;
import com.ingseoft.swapp.Repositories.UsuarioRepository;



@Component
public class ElementoTruequeService {

    // atributo
    private ElementoTruequeRepository repositorioElementosTrueque;
    private UsuarioRepository repositorioUsuarios;
    private CategoriaRepository repositorioCategorias;


    public ElementoTruequeService(
        ElementoTruequeRepository repositorioElementosTrueque,
        UsuarioRepository repositorioUsuarios,
        CategoriaRepository repositorioCategorias
    ) {
        this.repositorioElementosTrueque = repositorioElementosTrueque;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioCategorias = repositorioCategorias;
    }

    // Casos de uso
    public Iterable<ElementoTrueque> obtenerTodosLosElementoTrueque() {
        return this.repositorioElementosTrueque.findAll();
    }

    /*
     * CU003
     * Registrar elemento
     */
    public ElementoTrueque registrarElemento (CrearElementoTruequeDto nuevoElementoTrueque) throws Exception {
        // 1. Ingresa nombre del elemento.
        // 2. Ingresa enlace de imagen de elemento.
        // 3. Ingresa descripción.
        // 4. Se almacena inicialmente como disponible (disponible = true).
        // 5. Ingresa el precio del elemento.
        // 6. Se almacena inicialmente con estado ACTIVO (estadoElemento = "ACTIVO").
        ElementoTrueque parametro = new ElementoTrueque(
            nuevoElementoTrueque.getId(),
            nuevoElementoTrueque.getNombre(),
            nuevoElementoTrueque.getDescripcion(),
            nuevoElementoTrueque.getEnlaceImagen(),
            nuevoElementoTrueque.getPrecio(),
            true,
            "ACTIVO",
            new Usuario(),
            new Categoria(),
            new ArrayList<>()
        );

        // 7. Selecciona categoría del elemento.
        // 8. Valida que exista la categoría.
        Optional<Categoria> categoria = this.repositorioCategorias.findById(nuevoElementoTrueque.getCategoria_id());

        if(categoria.isEmpty()) {
            throw new Exception("No existe la categoría ingresada.");
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
            throw new Exception("El usuario registrado no existe en nuestra base de datos.");
        } else {
            parametro.setUsuario(usuario.get());
        }

        // 14. Almacena el elemento en la base de datos.
        return this.repositorioElementosTrueque.save(parametro);
    }

    /*
     * CU004
     * Borrar elemento
     */
    public String eliminarElemento (EliminarElementoTruequeDto elementoTrueque) throws Exception {

        // 1. El usuario ingresa el id del elemento a eliminar.
        Optional<ElementoTrueque> parametro = this.repositorioElementosTrueque.findById(elementoTrueque.getElemento_trueque_id());

        // 2. El sistema valida que exista el elemento a eliminar.
        if(parametro.isEmpty()) {
            throw new Exception("El elemento a eliminar no existe en nuestra base de datos.");
        } else {
            // 3. El sistema realiza un borrado lógico del elemento.
            this.repositorioElementosTrueque.delete(parametro.get());
        }

        return "El elemento con el nombre " + parametro.get().getNombre() + " ha sido eliminado.";
    }
}
