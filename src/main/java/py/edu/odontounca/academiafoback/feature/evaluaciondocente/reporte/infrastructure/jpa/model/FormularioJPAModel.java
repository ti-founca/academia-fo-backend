package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "evaluacion", name = "formulario")
@Getter @Setter
public class FormularioJPAModel {
    @Id
    @Column(name = "frm_id")
    private Integer id;

    @Column(name = "frm_descripcion", length = 100)
    private String descripcion;

    @Column(name = "frm_apertura")
    private LocalDateTime fechaApertura;

    @Column(name = "frm_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "frm_activo", nullable = false)
    private boolean activo;

    @Column(name = "frm_periodo", nullable = false)
    private Integer idPeriodo;

    @OneToMany(mappedBy = "formulario", fetch = FetchType.EAGER)
    private List<IndicadorJPAModel> indicadores;

    @ManyToOne
    @JoinColumn(name = "frm_tipo_eval")
    private TipoEvaluacionJPAModel tipoEvaluacion;
}
