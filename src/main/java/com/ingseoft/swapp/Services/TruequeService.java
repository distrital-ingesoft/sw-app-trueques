package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Model.Notificacion;
import com.ingseoft.swapp.Repositories.ElementoTruequeRepository;
import com.ingseoft.swapp.Repositories.TruequeRepository;
import com.ingseoft.swapp.Repositories.UsuarioRepository;



@Component
public class TruequeService {

    // Repositorios
    @Autowired
    private TruequeRepository repositorioTrueque;

    // Repositorios
    @Autowired
    private ElementoTruequeRepository repositorioElementoTrueque;

  
    // Repositorios
    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private Notificacion notificacion;

    public TruequeService(TruequeRepository repositorioTrueque) {
        this.repositorioTrueque = repositorioTrueque;
    }

    //------------------------ Casos de uso -----------------------------------------

    //CU005 Generar Reporte
    public Iterable<Trueque> obtenerTodosLosTrueques() {
        return this.repositorioTrueque.findAll();
    }
    

      //------------------------ Otros -----------------------------------------

    public Trueque agregarTrueque(Trueque nuevoTrueque) throws Exception {

            Trueque trueque = nuevoTrueque;

            //Almacenar Id usuario solicitante(Usuario) y solicitado(ElementoTrueque)
            Integer solicitanteId = trueque.getSolicitante().getId() ;
            trueque.setSolicitanteId(solicitanteId);

            Optional<ElementoTrueque> elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId());
            Integer solicitadoId = elemento.get().getUsuario().getId();
            trueque.setSolicitadoId(solicitadoId);

            //Estado inicial
            trueque.setEstado("Iniciado");

            //Fecha Inicio
            trueque.setFechaInicio(new Date());
            
            //Calcular Costo Logistica
            String CiudadSolicitante = repositorioUsuario.findById(solicitanteId).get().getCiudad();
            String CiudadSolicitado = repositorioUsuario.findById(solicitadoId).get().getCiudad();

            trueque.calcularLogistica(CiudadSolicitante, CiudadSolicitado);

            this.notificacion.enviarCorreo("nicolasrd1808@gmail.com", "test", "test mensaje");

            return this.repositorioTrueque.save(trueque);

    }

    public Optional<Trueque> ObtenerTrueque (Integer id){
        return this.repositorioTrueque.findById(id);
    }


    public Iterable<Trueque> ObtenerTruequebyUsuario (Integer id){
 
        //Concatenar truques como solicitado 
        List<Trueque> listaTrueques = new ArrayList<>(this.repositorioTrueque.findBySolicitanteId(id));
        listaTrueques.addAll(this.repositorioTrueque.findBySolicitadoId(id));
        return listaTrueques;
    }



    public String actualizarEstadoTrueque(Integer id, String estado) {
        Trueque trueque  = this.repositorioTrueque.findById(id).get();
        trueque.setEstado(estado);
        this.repositorioTrueque.save(trueque);

        return trueque.getEstado();
    }
}
