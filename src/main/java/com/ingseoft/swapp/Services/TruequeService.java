package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearTruequeDto;
import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Model.Usuario;
import com.ingseoft.swapp.Repositories.ElementoDeseadoRepository;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;
import com.ingseoft.swapp.Repositories.TruequeRepository;
import com.ingseoft.swapp.Repositories.UsuarioRepository;



@Component
public class TruequeService {

    // atributo
    private TruequeRepository repositorioTrueques;
    private UsuarioRepository repositorioUsuarios;
    private ElementoDeseadoRepository repositorioElementosDeseados;
    private ElementoTruequeRepository repositorioElementosTrueque;

    public TruequeService(
        TruequeRepository repositorioTrueques,
        UsuarioRepository repositorioUsuarios,
        ElementoDeseadoRepository repositorioElementosDeseados,
        ElementoTruequeRepository repositorioElementosTrueque
    ) {
        this.repositorioTrueques = repositorioTrueques;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioElementosDeseados = repositorioElementosDeseados;
        this.repositorioElementosTrueque = repositorioElementosTrueque;
    }

    // Casos de uso

    public Iterable<Trueque> obtenerTodosLosTrueques() {
        return this.repositorioTrueques.findAll();
    }


    public Trueque agregarTrueque(CrearTruequeDto nuevoTrueque) throws Exception {
        Trueque parametro = new Trueque(
            nuevoTrueque.getId(),
            nuevoTrueque.getEstado(),
            nuevoTrueque.getFechaInicio(),
            nuevoTrueque.getFechaFinal(),
            nuevoTrueque.getPrecioLogistica(),
            new Usuario(),
            new ElementoDeseado(),
            new ElementoTrueque()
        );

        Optional<Usuario> usuario = this.repositorioUsuarios.findById(nuevoTrueque.getSolicitante_id());

        if(usuario.isEmpty()) {
            throw new Exception("No existe el id del usuario ingresado.");
        } else {
            parametro.setSolicitante(usuario.get());
        }

        Optional<ElementoDeseado> elementoDeseado = this.repositorioElementosDeseados.findById(nuevoTrueque.getElementoDeseadoId());

        if(elementoDeseado.isEmpty()) {
            throw new Exception("No existe el id del elemento deseado ingresado.");
        } else {
            parametro.setElementoDeseado(elementoDeseado.get());
        }

        Optional<ElementoTrueque> elementoTrueque = this.repositorioElementosTrueque.findById(nuevoTrueque.getElementoTruequeId());

        if(elementoTrueque.isEmpty()) {
            throw new Exception("No existe el id del elemento deseado ingresado.");
        } else {
            parametro.setElementoTrueque(elementoTrueque.get());
        }

        return this.repositorioTrueques.save(parametro);
    }
}
