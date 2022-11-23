package com.ingseoft.swapp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CrearPermisoDto {

    Integer id;

    String nombre;

    String descripcion;

    Integer rol_id;
}
