package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Repositories.TruequeRepository;



@Component
public class TruequeService {

    // atributo
    @Autowired
    private TruequeRepository repositorio;

    // servicio
    @Autowired
    private ElementoTruequeService servicioElementoTrueque;


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
            Trueque trueque = this.repositorio.save(nuevoTrueque);
            trueque.setSolicitanteId(trueque.getSolicitante().getId().toString());
            Optional<ElementoTrueque> elemento = servicioElementoTrueque.ObtenerElementoTrueque(trueque.getElementoTrueque().getId());
            trueque.setSolicitadoId(elemento.get().getUsuario().getId().toString());
            return this.repositorio.save(trueque);

    }

    public Optional<Trueque> ObtenerTrueque (Integer id){
        return this.repositorio.findById(id);
    }


    public Iterable<Trueque> ObtenerTruequebyUsuario (String id){
        // Optional<Trueque> truquesSolicitante = this.repositorio.findBySolicitanteId(id);
        // Optional<Trueque> truquesSolicitados = this.repositorio.findBySolicitadoId(id);

        List<Trueque> listaTrueques = new ArrayList<>(this.repositorio.findBySolicitanteId(id));
        listaTrueques.addAll(this.repositorio.findBySolicitadoId(id));

        
        return listaTrueques;
    }
}
