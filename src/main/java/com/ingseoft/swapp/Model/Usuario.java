package com.ingseoft.swapp.Model;

import java.util.List;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private Integer id;
    
    @Column(name = "nombre_completo", length = 200)
    private String nombreCompleto;

    @Column(name = "documento_indentificacion")
    private Integer documentoIdentificacion;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "celular")
    private Long celular;

    @Column(name = "ciudad",length = 100)
    private String contrasenia;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Rol.class)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Usuarios_Roles"), nullable = true)
    private Rol rol;

    @Column(name = "estado")
    private Boolean estado;

    //@lazy--> no deja tener en ambas Fetch
    @OneToMany(mappedBy = "usuario")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ElementoTrueque> elementosTrueque;
    
    @OneToMany(mappedBy = "solicitante")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Trueque> trueques;
}
