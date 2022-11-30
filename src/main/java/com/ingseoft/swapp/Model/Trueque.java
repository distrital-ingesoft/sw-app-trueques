package com.ingseoft.swapp.Model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.ForeignKey;

@Table(name = "trueques")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Trueque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado",length = 100)
    private String estado;

    @Column(name= "fecha_inicio")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date fechaInicio;

    @Column(name= "fecha_final")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date fechaFinal;

    @Column(name = "precio_logistica",length = 18)
    private Double precioLogistica;

    @Column(name = "solicitante_id",length = 18)
    private String solicitanteId;


    @Column(name = "solicitado_id",length = 18)
    private String solicitadoId;


    //----------------Relaciones -------------------------------------------------------------------

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_trueques_usuario"), nullable = true)
    private Usuario solicitante;

    // @JsonBackReference
    // @ManyToMany(mappedBy = "trueques")
    // private List<Usuario> usuarios;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = ElementoDeseado.class)
    @JoinColumn(name = "elemento_deseado_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_trueques_elemento_deseado"), nullable = true)
    private ElementoDeseado elementoDeseado;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = ElementoTrueque.class)
    @JoinColumn(name = "elemento_trueque_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_trueques_elemento_trueque"), nullable = true)
    private ElementoTrueque elementoTrueque;

}
