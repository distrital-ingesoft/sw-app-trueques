package com.ingseoft.swapp.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class Trueque {
    @Id
    @GeneratedValue
    String estado;
    Date fecha;
    Double precioLogistica;

    @OneToOne
    ElementoDeseado elementoDeseado;

    @OneToMany
    List<ElementoTrueque> elementoTrueque;

    Trocador solicitante;
}
