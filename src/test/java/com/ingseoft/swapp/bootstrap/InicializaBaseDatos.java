package com.ingseoft.swapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializaBaseDatos implements CommandLineRunner {

    // al inicio del programa
    // antes de iniciar el servicio web
    @Override
    public void run(String... args) throws Exception {

        // carga datos iniciales
        // datos de configuración basicos que siempre van a la base de datos
    }
    
}
