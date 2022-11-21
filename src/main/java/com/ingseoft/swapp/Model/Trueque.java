package com.ingseoft.swapp.Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    Long id;

    String estado;

    @Temporal(TemporalType.DATE)
    Date fecha;

    Double precioLogistica;

    @OneToOne
    ElementoDeseado elementoDeseado;

    @OneToMany
    List<ElementoTrueque> elementosTrueque = new ArrayList<>();

    @ManyToOne
    Trocador solicitante;
}
