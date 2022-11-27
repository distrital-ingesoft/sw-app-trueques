package com.ingseoft.swapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Repositories.TruequeRepository;



@Component
public class TruequeService {

    // atributo
    @Autowired
    private TruequeRepository repositorio;


    public TruequeService(TruequeRepository repositorio) {
        this.repositorio = repositorio;
    }

    //------------------------ Casos de uso -----------------------------------------

    //CU005 Generar Reporte
    public Iterable<Trueque> obtenerTodosLosTrueques() {
        return this.repositorio.findAll();
    }
    

      //------------------------ Otros -----------------------------------------

    public Trueque agregarTrueque(Trueque nuevoTrueque) throws Exception {

            return this.repositorio.save(nuevoTrueque);

    }

    public Optional<Trueque> ObtenerTrueque (Integer id){
        return this.repositorio.findById(id);
    }
}
