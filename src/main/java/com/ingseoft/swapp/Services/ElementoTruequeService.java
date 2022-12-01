package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;



@Component
public class ElementoTruequeService {

    // atributo
    @Autowired
    private ElementoTruequeRepository repositorio;


    public ElementoTruequeService(ElementoTruequeRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso


    public Iterable<ElementoTrueque> obtenerTodosLosElementoTrueque() {
        return this.repositorio.findAll();
    }
    

    public ElementoTrueque agregarElementoTrueque (ElementoTrueque nuevoElementoTrueque){
        return this.repositorio.save(nuevoElementoTrueque);
    }

    public  Optional<ElementoTrueque> ObtenerElementoTrueque (Integer id){
        return this.repositorio.findById(id);
    }


    public Iterable<ElementoTrueque> ObtenerElementoTruequePorCategoria (Integer id){
        Categoria categoria = new Categoria();
        categoria.setId(id);
        return this.repositorio.findByCategoria(categoria);
    }

    public Boolean actualizarEstadoElementoTrueque(Integer id) {
        ElementoTrueque elementoTrueque =  ObtenerElementoTrueque(id).get();
        elementoTrueque.setDisponible(!elementoTrueque.getDisponible());
        this.repositorio.save(elementoTrueque);
        return elementoTrueque.getDisponible();
    }
}
