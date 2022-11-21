package com.ingseoft.swapp.Services;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Repositories.TruequeRepository;



@Component
public class TruequeService {

    // atributo
    private TruequeRepository repositorio;


    public TruequeService(TruequeRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Casos de uso

    public Iterable<Trueque> obtenerTodosLosTrueques() {
        return this.repositorio.findAll();
    }
    

    public Trueque agregarTrueque(Trueque nuevoTrueque) throws Exception {

            return this.repositorio.save(nuevoTrueque);

    }
}
