package com.ingseoft.swapp.Model;

import javax.persistence.Entity;

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
    String Categoria;
    String Description;
    Boolean Disponible;
    String EnlaceImagen;
    String EstadoElemento;
    String Nombre;
    Double Precio;
}
