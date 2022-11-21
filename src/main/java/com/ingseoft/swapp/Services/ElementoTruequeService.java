package com.ingseoft.swapp.Services;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;



@Component
public class ElementoTruequeService {

    // atributo
    private ElementoTruequeRepository repositorio;


    public ElementoTruequeService(ElementoTruequeRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<ElementoTrueque> obtenerTodosLosElementoTrueque() {
        return this.repositorio.findAll();
    }
    

    public ElementoTrueque agregarElementoTrueque (ElementoTrueque nuevoElementoTrueque) throws Exception {

            return this.repositorio.save(nuevoElementoTrueque);

    }
}
