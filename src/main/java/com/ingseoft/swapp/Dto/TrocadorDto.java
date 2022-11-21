package com.ingseoft.swapp.Dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TrocadorDto {

    Integer id;
    String ciudad;
    String direccion;
    String preferencia;

    public TrocadorDto(Integer id, String ciudad, String direccion, String preferencia){
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.preferencia = preferencia;
    }

}
