package com.ingseoft.swapp.Dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CrearRolDto {
    Integer id;

    private String nombre;

    private String descripcion;

    private Boolean activo;

    private List<Integer> permisos;
}
