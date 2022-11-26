package com.ingseoft.swapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingseoft.swapp.Dto.CrearTruequeDto;
import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Services.TruequeService;



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
    @GetMapping("/trueques")
    public Iterable<Trueque> leerTodosLosTrueque() {
        return this.servicio.obtenerTodosLosTrueques();
    }

    @PostMapping("/nuevo-trueque")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String solicitarTrueque(@RequestBody CrearTruequeDto nuevoTrueque) throws Exception {
        Trueque nuevo = this.servicio.solicitarTrueque(nuevoTrueque);
        return "Trueque Iniciado" + nuevo.getId().toString();
    }
}



