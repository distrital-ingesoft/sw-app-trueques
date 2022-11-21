package com.ingseoft.swapp.Dto;

import java.util.ArrayList;
import java.util.List;

import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Model.ElementoDeseado;
import com.ingseoft.swapp.Model.Trocador;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ElementoTruequeDto {

    Integer id;
    String description;
    Boolean disponible;
    String enlaceImagen;
    String estadoElemento;
    String nombre;
    Double precio;
    List<ElementoDeseado> elementosDeseados = new ArrayList<>();
    List<Categoria> categorias;
    Trocador trocador;

    public ElementoTruequeDto(Integer id, String description, Boolean disponible, String enlaceImagen, String estadoElemento, String nombre, Double precio, List<ElementoDeseado> elementoDeseados, List<Categoria> categorias, Trocador trocador){
        this.id = id;
        this.description = description;
        this.disponible = disponible;
        this.enlaceImagen = enlaceImagen;
        this.estadoElemento = estadoElemento;
        this.nombre = nombre;
        this.precio = precio;
        this.elementosDeseados = elementoDeseados;
        this.categorias = categorias;
        this.trocador = trocador;
    }

}
