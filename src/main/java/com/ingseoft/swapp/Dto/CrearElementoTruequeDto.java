package com.ingseoft.swapp.Dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CrearElementoTruequeDto {

    private Integer id;

    private String nombre;

    private String descripcion;

    private String enlaceImagen;

    private Double precio;

    private Boolean disponible;

    private String estadoElemento;

    private Integer usuario_id;

    private Integer categoria_id;

    private List<Integer> elementosDeseados;
}