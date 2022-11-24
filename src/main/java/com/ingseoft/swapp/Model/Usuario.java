package com.ingseoft.swapp.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ForeignKey;

@Table(name = "usuarios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "nombre_completo", length = 200)
    String nombreCompleto;

    @Column(name = "documento_indentificacion")
    Integer documentoIdentificacion;

    @Column(name = "correo", length = 100)
    String correo;

    @Column(name = "celular")
    Long celular;

    @Column(name = "ciudad",length = 100)
    String ciudad;

    @Column(name = "contrasenia",length = 100)
    String contrasenia;

    @Column(name = "activo")
    Boolean activo;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Rol.class)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Usuarios_Roles"), nullable = true)
    Rol rol;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
    })
    List<ElementoTrueque> elementosTrueque;
}
