package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "evaluacion", name = "tipo_evaluacion")
@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoEvaluacionJPAModel {
    @Id
    @Column(name = "tev_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "tev_nombre", length = 80)
    private String nombre;

    @Column(name = "tev_activo", nullable = false)
    private boolean activo;

    @Column(name = "tev_texto", length = 80)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "tev_tipo_docente")
    private TipoDocenteJPAModel tipoDocente;

    @ManyToOne
    @JoinColumn(name = "tev_estamento")
    private EstamentoJPAModel estamento;
}
