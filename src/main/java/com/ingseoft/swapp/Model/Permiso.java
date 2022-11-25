package com.ingseoft.swapp.Model;

import javax.persistence.CascadeType;
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

@Table(name = "permisos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "nombre",length = 100)
    String nombre;

    @Column(name = "descripcion", length = 250)
    String descripcion;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rol.class)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_permisos_rol"), nullable = true)
    Rol rol;
}
