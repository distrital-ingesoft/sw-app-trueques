package com.ingseoft.swapp.Dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CrearUsuarioDto {

    Integer id;

    String nombreCompleto;

    Integer documentoIdentificacion;

    String correo;

    Long celular;

    String ciudad;

    String contrasenia;

    Boolean activo;

    Integer rol_id;

    List<Integer> elementos_trueque;
}
