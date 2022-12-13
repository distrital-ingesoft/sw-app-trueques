package com.ingseoft.swapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Repositories.CategoriaRepository;



@Component
public class CategoriaService {

    // atributo
    @Autowired
    private CategoriaRepository repositorio;


    public CategoriaService(CategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<Categoria> obtenerTodosLasCategorias() {
        return this.repositorio.findAll();
    }


    public Categoria agregarCategoria(Categoria nuevaCategoria) throws Exception {
        return this.repositorio.save(nuevaCategoria);
    }
}
