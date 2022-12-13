package com.ingseoft.swapp.Model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// import org.hibernate.annotations.LazyCollection;
// import org.hibernate.annotations.LazyCollectionOption;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.ForeignKey;


@Table(name = "elementos_trueque")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class,
//   property = "id")
public class ElementoTrueque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre",length = 100)
    private String nombre;

    @Column(name = "descripcion",length = 250)
    private String descripcion;

    @Column(name = "enlace_imagen",length = 500)
    private String enlaceImagen;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "estado_elemento",length = 100)
    private String estadoElemento;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_elementos_trueque_usuarios"), nullable = true)
    private Usuario usuario;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Categoria.class)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_elementos_trueque_categorias"), nullable = true)
    private Categoria categoria;


    // @OneToMany(mappedBy = "elementoTrueque")
    // @LazyCollection(LazyCollectionOption.FALSE)
    // private List<ElementoDeseado> elementosDeseados;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ElementoDeseado> elementosDeseados = new ArrayList<>();


}
