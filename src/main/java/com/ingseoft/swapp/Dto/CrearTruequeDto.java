package com.ingseoft.swapp.Dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CrearTruequeDto {

    private Integer id;

    private String estado;

    private Date fechaInicio;

    private Date fechaFinal;

    private Double precioLogistica;

    private Integer solicitante_id;

    private Integer elementoDeseadoId;

    private Integer elementoTruequeId;
}
