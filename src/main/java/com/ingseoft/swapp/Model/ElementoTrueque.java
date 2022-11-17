package com.ingseoft.swapp.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ElementoTrueque {
    @Id
    @GeneratedValue
    Integer id;
    String description;
    Boolean disponible;
    String enlaceImagen;
    String estadoElemento;
    String nombre;
    Double precio;
    List<ElementoDeseado> elementosDeseados = new ArrayList<>();
    Categoria categoria;
    Trocador trocador;
}
