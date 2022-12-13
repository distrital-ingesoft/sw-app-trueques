package com.ingseoft.swapp.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public Iterable<Trueque> obtenerTodosLosTrueques() {
        return this.repositorioTrueque.findAll();
    }

    public Trueque solicitarTrueque(Trueque nuevoTrueque) throws Exception {
        Optional<Usuario> usuarioSolicitante =  this.repositorioUsuario.findById(nuevoTrueque.getSolicitante().getId());

        if(usuarioSolicitante.isEmpty()) {
            throw new Exception("No existe usuario solicitante");
        }

        Trueque trueque = nuevoTrueque;

        trueque.setSolicitante(usuarioSolicitante.get());

        int elementoTruequeId = trueque.getElementoTrueque().getId();
        Optional<ElementoTrueque> elementoTrueque = this.repositorioElementoTrueque.findById(elementoTruequeId);

        if(elementoTrueque.isEmpty()) {
            throw new Exception("El elemento solicitado no existe");
        }

        if(!elementoTrueque.get().getDisponible()) {
            throw new Exception("El elemento no esta activo o disponible");
        }

        if(elementoTrueque.get().getUsuario().getId() == trueque.getSolicitanteId()) {
            throw new Exception("No se puede trocar un elemento de tu propiedad");
        }

        trueque.setElementoTrueque(elementoTrueque.get());

        trueque.setEstado("INICIADO");

        trueque.setFechaInicio(new Date());

        //Calcular Costo Logistica
        Usuario usuarioSolicitado = trueque.getElementoTrueque().getUsuario();

        String CiudadSolicitante = usuarioSolicitante.get().getCiudad();
        String CiudadSolicitado = usuarioSolicitado.getCiudad();

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

    //Aceptar Trueque
    public String aceptarTrueque(Integer id) throws Exception {
        Optional<Trueque> trueque  = this.repositorioTrueque.findById(id);

        if(trueque.get().getEstado().equals("INICIADO")) {
            this.cambiarEstadoTrueque(trueque, "ACEPTADO");
            return trueque.get().getEstado();
        } else {
            throw new Exception("El trueque debe esta en estado INICIADO");
        }
    }

    //Rechazar Trueque
    public String rechazarTrueque(Integer id) throws Exception {
        Optional<Trueque> trueque  = this.repositorioTrueque.findById(id);

        if(trueque.get().getEstado().equals("INICIADO")) {
            this.cambiarEstadoTrueque(trueque, "RECHAZADO");
            return trueque.get().getEstado();
        } else {
            throw new Exception("El trueque debe esta en estado INICIADO");
        }
    }

    //Cancelar Trueque
    public String cancelarTrueque(Integer id) throws Exception {
        Optional<Trueque> trueque  = this.repositorioTrueque.findById(id);

        if(trueque.get().getEstado().equals("INICIADO")) {
            this.cambiarEstadoTrueque(trueque, "CANCELADO");
            return trueque.get().getEstado();
        } else {
            throw new Exception("El trueque debe esta en estado INICIADO");
        }
    }

    //Finalizar Trueque
    public String finalizarTrueque(Integer id) throws Exception {
        Optional<Trueque> trueque  = this.repositorioTrueque.findById(id);

        if(trueque.get().getEstado().equals("ACEPTADO")) {
            this.cambiarEstadoTrueque(trueque, "FINALIZADO");
            return trueque.get().getEstado();
        } else {
            throw new Exception("El trueque debe esta en estado ACEPTADO");
        }
    }

    private Trueque cambiarEstadoTrueque(Optional<Trueque> trueque, String state) throws Exception {
        if(trueque.isEmpty()) {
            throw new Exception("El trueque no existe");
        }

        Optional<Usuario> usuarioSolicitante = repositorioUsuario.findById(trueque.get().getSolicitante().getId());

        if(usuarioSolicitante.isEmpty()) {
            throw new Exception("No existe usuario solicitante");
        }

        Optional<ElementoTrueque> elementoTrueque = repositorioElementoTrueque.findById(trueque.get().getElementoTrueque().getId());

        if(elementoTrueque.isEmpty()) {
            throw new Exception("El elemento asociado al trueque no existe");
        }

        trueque.get().setEstado(state);

        //Consultar usuarios involucrados
        Usuario usuarioSolicitado = repositorioUsuario.findById(elementoTrueque.get().getUsuario().getId()).get();

        Boolean truequeDisponible = state == "RECHAZADO" || state == "CANCELADO";

        //Actualizacion disponibilidad elemento trueque a true
        elementoTrueque.get().setDisponible(truequeDisponible);
        this.repositorioElementoTrueque.save(elementoTrueque.get());

        //Envio de correo
        this.notificacion.enviarCorreo(usuarioSolicitante.get().getCorreo(), "Actualización estado Trueque", "Trueque " + state + "por " + elementoTrueque.get().getNombre());
        this.notificacion.enviarCorreo(usuarioSolicitado.getCorreo(), "Actualización estado Trueque", "Trueque " + state + " por " + elementoTrueque.get().getNombre());

        this.repositorioTrueque.save(trueque.get());

        return trueque.get();
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
