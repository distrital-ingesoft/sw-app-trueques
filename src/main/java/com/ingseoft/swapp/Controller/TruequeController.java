package com.ingseoft.swapp.Controller;


import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Services.TruequeService;


@CrossOrigin("*")
@RestController
public class TruequeController {

    // servicio
    @Autowired
    private TruequeService servicio;

    // constructor -- recibe este parámetro
    // Spring al momento de crear este componentes, va a crear las dependencias
    // a inyectar las dependencias

    public TruequeController(TruequeService servicio) {
        this.servicio = servicio;
    }

    // == Operaciones REST


    @GetMapping("/")
    public String getMessage(){
        return "Hello World";
    }


    @GetMapping("/Trueques")
    public Iterable<Trueque> leerTodosLosTrueque() {
        return this.servicio.obtenerTodosLosTrueques();
    }

    @GetMapping("/TruequesXlsx")
    public ResponseEntity<InputStreamResource> leerTodosLosTruequeXlsx() {
        ByteArrayInputStream stream = this.servicio.exportAllTrueques();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=trueques.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/Trueques/{id}")
    public Iterable<Trueque> ObtenerTruequeUsuario(@PathVariable Integer id) {
        return this.servicio.ObtenerTruequebyUsuario(id);
    }

    @PostMapping("/nuevoTrueque")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Trueque agregarTrueque(@RequestBody Trueque nuevoTrueque) throws Exception {
        Trueque nuevo = this.servicio.solicitarTrueque(nuevoTrueque);
        //return "Trueque Iniciado" + nuevo.getId().toString();
        return nuevo;
    }

    // //http://127.0.0.1:8080/TruequeEstado/1?estado=Finalizado
    // @PutMapping("/TruequeEstado/{Id}")
    // public String actualizarEstadoTrueque(@PathVariable Integer Id, @RequestParam String estado)  {
    //     String nuevoEstado  = this.servicio.actualizarEstadoTrueque(Id ,estado);
    //     return nuevoEstado;
    // }

    //http://127.0.0.1:8080/Trueque/Aceptar/1
    @PutMapping("/Trueque/Aceptar/{Id}")
    public String aceptarTrueque(@PathVariable Integer Id)  {
        String nuevoEstado  = this.servicio.aceptarTrueque(Id);
        return nuevoEstado;
    }

    //http://127.0.0.1:8080/Trueque/Rechazar/1
    @PutMapping("/Trueque/Rechazar/{Id}")
    public String rechazarTrueque(@PathVariable Integer Id)  {
        String nuevoEstado  = this.servicio.rechazarTrueque(Id);
        return nuevoEstado;
    }

    //http://127.0.0.1:8080/Trueque/Cancelar/1
    @PutMapping("/Trueque/Cancelar/{Id}")
    public String cancelarTrueque(@PathVariable Integer Id)  {
        String nuevoEstado  = this.servicio.cancelarTrueque(Id);
        return nuevoEstado;
    }

    //http://127.0.0.1:8080/Trueque/Finalizar/1
    @PutMapping("/Trueque/Finalizar/{Id}")
    public String finalizarTrueque(@PathVariable Integer Id)  {
        String nuevoEstado  = this.servicio.finalizarTrueque(Id);
        return nuevoEstado;
    }
}



