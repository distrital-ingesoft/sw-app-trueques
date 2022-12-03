package com.ingseoft.swapp.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
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
    private String ciudad;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "contrasena")
    private String contrasena;



    //---------------------Relaciones------------------------------------------------

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rol.class)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Usuarios_Roles"), nullable = true)
    private Rol rol;

    //@lazy--> no deja tener en ambas Fetch
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ElementoTrueque> elementosTrueque;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "solicitante")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Trueque> trueques;

    // @ManyToMany
    // @JoinTable(name = "usuarios_trueque", joinColumns = @JoinColumn(name = "usuario_id"),  inverseJoinColumns = @JoinColumn(name = "trueque_id"))
    // private List<Trueque> trueques;

}
