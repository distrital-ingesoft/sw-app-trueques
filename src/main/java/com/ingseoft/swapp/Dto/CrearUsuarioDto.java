package com.ingseoft.swapp.Dto;

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

    Integer rol_id;

    Boolean activo;
}
