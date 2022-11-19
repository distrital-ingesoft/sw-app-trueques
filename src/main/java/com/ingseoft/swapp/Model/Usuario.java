package com.ingseoft.swapp.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Usuario")
@Entity
public class Usuario {
    Integer celular;
    String contrasenia;
    String correo;
    Integer documentoIdentificacion;
    String nombreCompleto;
    String rol;
}
