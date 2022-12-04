package com.ingseoft.swapp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/Trueques/id")
    public Iterable<Trueque> ObtenerTruequeUsuario(@RequestParam Integer id) {
        return this.servicio.ObtenerTruequebyUsuario(id);
    }

    @PostMapping("/nuevoTrueque")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Trueque agregarTrueque(@RequestBody Trueque nuevoTrueque) throws Exception {
        Trueque nuevo = this.servicio.agregarTrueque(nuevoTrueque);
        //return "Trueque Iniciado" + nuevo.getId().toString();
        return nuevo;
    }

    //http://127.0.0.1:8080/TruequeEstado?Id=1&estado=Finalizado
    @PutMapping("/TruequeEstado")
    public String actualizarEstadoTrueque(@RequestParam Integer Id, @RequestParam String estado)  {
        String nuevoEstado  = this.servicio.actualizarEstadoTrueque(Id ,estado);
        return nuevoEstado;
    }


}



