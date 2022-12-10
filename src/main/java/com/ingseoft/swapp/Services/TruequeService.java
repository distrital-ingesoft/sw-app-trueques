package com.ingseoft.swapp.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
            // Traer le usuario
            Optional<Usuario> usuarioSolicitante =  this.repositorioUsuario.findById(nuevoTrueque.getSolicitanteId());

            if(usuarioSolicitante.isEmpty()) {
                throw new Exception("No existe usuario solicitante.");
            }

            Trueque trueque = nuevoTrueque;

            trueque.setSolicitante(usuarioSolicitante.get());

            int elementoTruequeId = trueque.getElementoTrueque().getId();
            Optional<ElementoTrueque> elementoTrueque = this.repositorioElementoTrueque.findById(elementoTruequeId);

            if(elementoTrueque.isEmpty()) {
                throw new Exception("Elemento solicitado no existe");
            }

            if(!elementoTrueque.get().getDisponible()) {
                throw new Exception("Elemento no esta activo o disponible");
            }

            if(elementoTrueque.get().getUsuario().getId() == trueque.getSolicitanteId()) {
                throw new Exception("No se puede trocar un elemento de tu propiedad");
            }

            trueque.setElementoTrueque(elementoTrueque.get());

            trueque.setEstado("INICIADO");

            trueque.setFechaInicio(new Date());

            //Calcular Costo Logistica
            // Usuario usuarioSolicitante = trueque.getSolicitante();
            Usuario usuarioSolicitado = trueque.getElementoTrueque().getUsuario();

            String CiudadSolicitante = usuarioSolicitante.get().getCiudad();
            String CiudadSolicitado = usuarioSolicitado.getCiudad();

            // trueque.calcularLogistica(trueque.getSolicitante().getCiudad(), trueque.getElementoTrueque().getUsuario().getCiudad());

            trueque.calcularLogistica(CiudadSolicitante, CiudadSolicitado);

            //Actualizacion disponibilidad elemento trueque a false
            elementoTrueque.get().setDisponible(false);
            repositorioElementoTrueque.save(elementoTrueque.get());

            //Guardar trueque
            trueque = this.repositorioTrueque.save(trueque);

            //Envio de correo
            this.notificacion.enviarCorreo(usuarioSolicitante.get().getCorreo(), "Actualización estado Truque", "Trueque INICIADO por " + elementoTrueque.get().getNombre());
            this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Truque", "Trueque INICIADO por " + elementoTrueque.get().getNombre());

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
        String[] columns = {"Id","Estado","Fecha de inicio","Fecha final","Precio de logística"};

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Trueques");
        Row row = sheet.createRow(0);
        for(int i = 0; i < columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        List<Trueque> trueques = this.repositorioTrueque.findAll();
        int initRow = 1;
        for(Trueque trueque: trueques) {
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(trueque.getId());
            row.createCell(1).setCellValue(trueque.getEstado());

            if (trueque.getFechaInicio() != null) {
                row.createCell(2).setCellValue(simpleDateFormat.format(trueque.getFechaInicio()));
            } else {
                row.createCell(2).setCellValue("");
            }

            if (trueque.getFechaFinal() != null) {
                row.createCell(3).setCellValue(simpleDateFormat.format(trueque.getFechaFinal()));
            } else {
                row.createCell(3).setCellValue("");
            }

            row.createCell(4).setCellValue(trueque.getPrecioLogistica());

            initRow++;
        }

        try {
            workbook.write(stream);
            workbook.close();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
