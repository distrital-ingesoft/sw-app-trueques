package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CambiarEstadoTruequeDto;
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

    /*
     * CU005
     * Solicitar trueque
     */
    public Trueque solicitarTrueque(CrearTruequeDto nuevoTrueque) throws Exception {

        // 1. Ingresa fecha de inicio.
        // 2. Ingresa fecha de finalización.
        Trueque parametro = new Trueque(
            nuevoTrueque.getId(),
            "ACTIVO",
            nuevoTrueque.getFechaInicio(),
            nuevoTrueque.getFechaFinal(),
            nuevoTrueque.getPrecioLogistica(),
            new Usuario(),
            new ElementoDeseado(),
            new ElementoTrueque()
        );

        Optional<ElementoTrueque> elementoTrueque = this.repositorioElementosTrueque.findById(nuevoTrueque.getElemento_trueque_id());
        elementoTrueque.get().setEstadoElemento("TRUEQUE");

        if(elementoTrueque.isEmpty()) {
            throw new Exception("No existe el id del elemento deseado ingresado.");
        } else {
            parametro.setElementoTrueque(elementoTrueque.get());
        }

        Optional<Usuario> usuario = this.repositorioUsuarios.findById(elementoTrueque.get().getUsuario().getId());

        if(usuario.isEmpty()) {
            throw new Exception("No existe el id del usuario ingresado.");
        } else {
            parametro.setSolicitante(usuario.get());
        }

        Optional<ElementoDeseado> elementoDeseado = this.repositorioElementosDeseados.findById(nuevoTrueque.getElemento_deseado_id());

        if(elementoDeseado.isEmpty()) {
            throw new Exception("No existe el id del elemento deseado ingresado.");
        } else {
            parametro.setElementoDeseado(elementoDeseado.get());
        }

        return this.repositorioTrueques.save(parametro);
    }

    public String cambiarEstadoTrueque(CambiarEstadoTruequeDto truequeDto) throws Exception {
        Optional<Trueque> trueque = this.repositorioTrueques.findById(truequeDto.getTrueque_id());

        if(trueque.isEmpty()) {
            throw new Exception("No existe el trueque.");
        } else {
            trueque.get().setEstado(truequeDto.getEstado());
        }

        Optional<ElementoTrueque> elementoTrueque = this.repositorioElementosTrueque.findById(trueque.get().getElementoTrueque().getId());

        if(elementoTrueque.isEmpty()) {
            throw new Exception("No existe el trueque.");
        } else {
            String estadoElementoTrueque;

            switch (truequeDto.getEstado()) {
                case "RECHAZADO":
                case "CANCELADO":
                    estadoElementoTrueque = "ACTIVO";
                    break;

                case "ACEPTADO":
                    estadoElementoTrueque = "NO DISPONIBLE";
                    break;

                default:
                    estadoElementoTrueque = "TRUEQUE";
                    break;
            }

            elementoTrueque.get().setEstadoElemento(estadoElementoTrueque);
        }

        return "Estado de trueque cambiado";
    }
}
