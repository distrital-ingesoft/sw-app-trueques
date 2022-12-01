package com.ingseoft.swapp.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    // servicio
    @Autowired
    private UsuarioService servicioUsuario;


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

            //Almacenar Id usuario solicitante(Usuario) y solicitado(ElementoTrueque)
            String solicitanteId = trueque.getSolicitante().getId().toString() ;
            trueque.setSolicitanteId(solicitanteId);
            Optional<ElementoTrueque> elemento = servicioElementoTrueque.ObtenerElementoTrueque(trueque.getElementoTrueque().getId());
            String solicitadoId = elemento.get().getUsuario().getId().toString();
            trueque.setSolicitadoId(solicitadoId);

            //Estado inicial
            trueque.setEstado("Iniciado");

            //Fecha Inicio
            Date date = new Date();
            trueque.setFechaInicio(date);
            
            //Calcular Costo Logistica
            String CiudadSolicitante = servicioUsuario.ObtenerUsuario(Integer.parseInt(solicitanteId)).getCiudad();
            String CiudadSolicitado = servicioUsuario.ObtenerUsuario(Integer.parseInt(solicitadoId)).getCiudad();
            Double precioLogistica = trueque.calcularLogistica(CiudadSolicitante, CiudadSolicitado);
            trueque.setPrecioLogistica(precioLogistica);

            return this.repositorio.save(trueque);

    }

    public Optional<Trueque> ObtenerTrueque (Integer id){
        return this.repositorio.findById(id);
    }


    public Iterable<Trueque> ObtenerTruequebyUsuario (String id){
 
        //Concatenar truques como solicitado 
        List<Trueque> listaTrueques = new ArrayList<>(this.repositorio.findBySolicitanteId(id));
        listaTrueques.addAll(this.repositorio.findBySolicitadoId(id));

        
        return listaTrueques;
    }



    public String actualizarEstadoTrueque(Integer id, String estado) {
        Trueque trueque  = this.repositorio.findById(id).get();
        trueque.setEstado(estado);
        this.repositorio.save(trueque);

        return trueque.getEstado();
    }
}
