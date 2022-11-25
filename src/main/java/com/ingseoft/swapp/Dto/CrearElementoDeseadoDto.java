package com.ingseoft.swapp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CrearElementoDeseadoDto {

    private Integer id;

    private String nombre;

    private Integer elemento_trueque_id;
}
