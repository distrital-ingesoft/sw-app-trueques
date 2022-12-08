package com.ingseoft.swapp.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
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

    public Trueque solicitarTrueque(Trueque nuevoTrueque) throws Exception {

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

            //Actualizacion disponibilidad elemento trueque a false
            elemento.setDisponible(false);
            repositorioElementoTrueque.save(elemento);

            //Guardar trueque
            trueque = this.repositorioTrueque.save(trueque);


            //Envio de correo
            this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque INICIADO por " + elemento.getNombre());
            this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque INICIADO por " + elemento.getNombre());

            return trueque;

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



    // public String actualizarEstadoTrueque(Integer id, String estado) {
    //     Trueque trueque  = this.repositorioTrueque.findById(id).get();
    //     trueque.setEstado(estado);
    //     trueque.setFechaFinal(new Date());

    //     ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
    //     Usuario usuarioSolicitante = repositorioUsuario.findById(trueque.getSolicitanteId()).get();
    //     Usuario usuarioSolicitado = repositorioUsuario.findById(trueque.getSolicitadoId()).get();

    //     if(estado.equals("ACEPTADO")){


    //     }else if(estado.equals("RECHAZADO")){
    //         //Actualizacion disponibilidad elemento trueque a true
    //         elemento.setDisponible(true);
    //         repositorioElementoTrueque.save(elemento);


    //     }else if(estado.equals("CANCELADO")){

    //         //Actualizacion disponibilidad elemento trueque a true
    //         elemento.setDisponible(true);
    //         repositorioElementoTrueque.save(elemento);

    //     }else if(estado.equals("FINALIZADO"))

    //     //Envio de correo
    //     this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque " + estado + " por " + elemento.getNombre());
    //     this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque " + estado + " por " + elemento.getNombre());

    //     this.repositorioTrueque.save(trueque);

    //     return trueque.getEstado();
    // }

    //Aceptar Trueque
    public String aceptarTrueque(Integer id) {
        Trueque trueque  = this.repositorioTrueque.findById(id).get();
        trueque.setEstado("ACEPTADO");

        //Consultar usuarios involucrados
        ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
        Usuario usuarioSolicitante = repositorioUsuario.findById(trueque.getSolicitanteId()).get();
        Usuario usuarioSolicitado = repositorioUsuario.findById(trueque.getSolicitadoId()).get();

        //Envio de correo
        this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque ACEPTADO por " + elemento.getNombre());
        this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque ACEPTADO por " + elemento.getNombre());

        this.repositorioTrueque.save(trueque);

        return trueque.getEstado();
    }

    //Rechazar Trueque
    public String rechazarTrueque(Integer id) {
        Trueque trueque  = this.repositorioTrueque.findById(id).get();
        trueque.setEstado("RECHAZADO");

        //Consultar usuarios involucrados
        ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
        Usuario usuarioSolicitante = repositorioUsuario.findById(trueque.getSolicitanteId()).get();
        Usuario usuarioSolicitado = repositorioUsuario.findById(trueque.getSolicitadoId()).get();

        //Actualizacion disponibilidad elemento trueque a true
        elemento.setDisponible(true);
        repositorioElementoTrueque.save(elemento);

        //Envio de correo
        this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque RECHAZADO por " + elemento.getNombre());
        this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque RECHAZADO por " + elemento.getNombre());

        this.repositorioTrueque.save(trueque);

        return trueque.getEstado();
    }


    //Cancelar Trueque
    public String cancelarTrueque(Integer id) {
        Trueque trueque  = this.repositorioTrueque.findById(id).get();
        trueque.setEstado("CANCELADO");

        //Consultar usuarios involucrados
        ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
        Usuario usuarioSolicitante = repositorioUsuario.findById(trueque.getSolicitanteId()).get();
        Usuario usuarioSolicitado = repositorioUsuario.findById(trueque.getSolicitadoId()).get();

        //Actualizacion disponibilidad elemento trueque a true
        elemento.setDisponible(true);
        repositorioElementoTrueque.save(elemento);

        //Envio de correo
        this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque CANCELADO por " + elemento.getNombre());
        this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque CANCELADO por " + elemento.getNombre());

        this.repositorioTrueque.save(trueque);

        return trueque.getEstado();
    }

    //Finalizar Trueque
    public String finalizarTrueque(Integer id) {
        Trueque trueque  = this.repositorioTrueque.findById(id).get();
        trueque.setEstado("FINALIZADO");

        //Consultar usuarios involucrados
        ElementoTrueque elemento = repositorioElementoTrueque.findById(trueque.getElementoTrueque().getId()).get();
        Usuario usuarioSolicitante = repositorioUsuario.findById(trueque.getSolicitanteId()).get();
        Usuario usuarioSolicitado = repositorioUsuario.findById(trueque.getSolicitadoId()).get();

        //Envio de correo
        this.notificacion.enviarCorreo(usuarioSolicitante.getCorreo(), "Actualización estado Truque", "Trueque FINALIZADO por " + elemento.getNombre());
        this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque FINALIZADO por " + elemento.getNombre());

        this.repositorioTrueque.save(trueque);

        return trueque.getEstado();
    }

    public ByteArrayInputStream exportAllTrueques() {
        String[] columns = {"Id","Estado","Fecha de inicio","Fecha final","Precio de logística","Id de solicitante","Id del solicitado"};

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Trueques");
        Row row = sheet.createRow(0);
        for(int i = 0; i < columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Trueque> trueques = this.repositorioTrueque.findAll();
        int initRow = 1;
        for(Trueque trueque: trueques) {
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(trueque.getId());
            row.createCell(0).setCellValue(trueque.getEstado());
            row.createCell(0).setCellValue(trueque.getFechaInicio());
            row.createCell(0).setCellValue(trueque.getFechaFinal());
            row.createCell(0).setCellValue(trueque.getPrecioLogistica());
            row.createCell(0).setCellValue(trueque.getSolicitanteId());
            row.createCell(0).setCellValue(trueque.getSolicitadoId());

            initRow++;
        }

        try {
            workbook.write(stream);
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
