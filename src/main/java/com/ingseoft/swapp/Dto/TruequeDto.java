package com.ingseoft.swapp.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Model.ElementoTrueque;
import com.ingseoft.swapp.Model.Trocador;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TruequeDto {

    Long id;
    String estado;
    Date fecha;
    Double precioLogistica;
    ElementoDeseado elementoDeseado;
    List<ElementoTrueque> elementosTrueque = new ArrayList<>();
    Trocador solicitante;

    public TruequeDto(Long id, String estado, Date fecha, Double precioLogistica, ElementoDeseado elementoDeseado, List<ElementoTrueque> elementosTrueque, Trocador solicitante) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.precioLogistica = precioLogistica;
        this.elementoDeseado = elementoDeseado;
        this.elementosTrueque = elementosTrueque;
        this.solicitante = solicitante;
    }

}
