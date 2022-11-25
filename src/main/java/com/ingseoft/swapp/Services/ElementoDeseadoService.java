package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Dto.CrearElementoDeseadoDto;
import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Repositories.ElementoDeseadoRepository;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;



@Component
public class ElementoDeseadoService {

    // atributo
    private ElementoDeseadoRepository repositorioElementoDeseado;
    private ElementoTruequeRepository repositorioElementoTrueque;

    public ElementoDeseadoService(
        ElementoDeseadoRepository repositorioElementoDeseado,
        ElementoTruequeRepository repositorioElementoTrueque
    ) {
        this.repositorioElementoDeseado = repositorioElementoDeseado;
        this.repositorioElementoTrueque = repositorioElementoTrueque;
    }

    // Casos de uso

    public Iterable<ElementoDeseado> obtenerTodosLosElementoDeseados() {
        return this.repositorioElementoDeseado.findAll();
    }

    public ElementoDeseado agregarElementoDeseado (CrearElementoDeseadoDto nuevoElementoDeseado) throws Exception {
        ElementoDeseado parametro = new ElementoDeseado(
            nuevoElementoDeseado.getId(),
            nuevoElementoDeseado.getNombre(),
            new ElementoTrueque()
        );

        Optional<ElementoTrueque> elementoTrueque = this.repositorioElementoTrueque.findById(nuevoElementoDeseado.getElementoTruequeId());

        if(elementoTrueque.isEmpty()) {
            throw new Exception("No existe el id del elemento para trueque.");
        } else {
            parametro.setElementoTrueque(elementoTrueque.get());
        }

        return this.repositorioElementoDeseado.save(parametro);
    }
}
