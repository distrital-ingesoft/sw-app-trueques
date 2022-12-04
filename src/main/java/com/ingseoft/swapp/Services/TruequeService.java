package com.ingseoft.swapp.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Model.Usuario;
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

            ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
            Integer solicitadoId = elemento.getUsuario().getId();
            trueque.setSolicitadoId(solicitadoId);

            //Estado inicial
            trueque.setEstado("INICIADO");

            //Fecha Inicio
            trueque.setFechaInicio(new Date());
            
            //Calcular Costo Logistica
            Usuario usuarioSolicitante = repositorioUsuario.findById(solicitanteId).get();
            Usuario usuarioSolicitado = repositorioUsuario.findById(solicitadoId).get();

            String CiudadSolicitante = usuarioSolicitante.getCiudad();
            String CiudadSolicitado = usuarioSolicitado.getCiudad();

            trueque.calcularLogistica(CiudadSolicitante, CiudadSolicitado);

            //Envio de correo

            this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque INICIADO por " + elemento.getNombre());
            this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque INICIADO por " + elemento.getNombre());

            //Actualizacion disponibilidad elemento trueque a false
            elemento.setDisponible(false);
            repositorioElementoTrueque.save(elemento);

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

        ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
        Usuario usuarioSolicitante = repositorioUsuario.findById(trueque.getSolicitanteId()).get();
        Usuario usuarioSolicitado = repositorioUsuario.findById(trueque.getSolicitadoId()).get();

        if(estado.equals("ACEPTADO")){


        }else if(estado.equals("RECHAZADO")){
            //Actualizacion disponibilidad elemento trueque a true
            elemento.setDisponible(true);
            repositorioElementoTrueque.save(elemento);


        }else if(estado.equals("CANCELADO")){

            //Actualizacion disponibilidad elemento trueque a true
            elemento.setDisponible(true);
            repositorioElementoTrueque.save(elemento);

        }else if(estado.equals("FINALIZADO"))

        //Envio de correo
        this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque " + estado + " por " + elemento.getNombre());
        this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque " + estado + " por " + elemento.getNombre());

        this.repositorioTrueque.save(trueque);

        return trueque.getEstado();
    }
}
