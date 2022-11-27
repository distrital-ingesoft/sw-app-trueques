package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Repositories.ElementoDeseadoRepository;



@Component
public class ElementoDeseadoService {

    // atributo
    @Autowired
    private ElementoDeseadoRepository repositorio;


    public ElementoDeseadoService(ElementoDeseadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<ElementoDeseado> obtenerTodosLosElementoDeseados() {
        return this.repositorio.findAll();
    }
    

    public ElementoDeseado agregarElementoDeseado (ElementoDeseado nuevoElementoDeseado) throws Exception {

        return this.repositorio.save(nuevoElementoDeseado);

    }

    public Optional<ElementoDeseado> ObtenerElementoDeseado (Integer id){
        return this.repositorio.findById(id);
    }
}
