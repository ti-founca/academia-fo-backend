package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "evaluacion", name = "evaluacion")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EvaluacionJPAModel {
    @Id
    @Column(name = "eva_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "eva_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "eva_fin")
    private LocalDateTime fechaFin;

    @Column(name = "eva_terminado", nullable = false)
    private Boolean terminado;

    @OneToMany(mappedBy = "evaluacion")
    private List<DetalleEvaluacionJPAModel> detalles;

    @ManyToOne
    @JoinColumn(name = "eva_materia")
    private MateriaJPAModel materia;

    @ManyToOne
    @JoinColumn(name = "eva_evaluador")
    private PersonaJPAModel evaluador;

    @ManyToOne
    @JoinColumn(name = "eva_evaluado")
    private PersonaJPAModel evaluado;

    @ManyToOne
    @JoinColumn(name = "eva_formulario")
    private FormularioJPAModel formulario;
}
