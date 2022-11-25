package com.ingseoft.swapp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.ForeignKey;


@Table(name = "elementos_deseados")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ElementoDeseado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre",length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ElementoTrueque.class)
    @JoinColumn(name = "elemento_trueque_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_elementos_deseados_elemento_trueque"), nullable = true)
    private ElementoTrueque elementoTrueque;
}
