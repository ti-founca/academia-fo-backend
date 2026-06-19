package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "evaluacion", name = "evaluacion_detalle")
@Getter @Setter @RequiredArgsConstructor
public class DetalleEvaluacionJPAModel {
    @Id
    private Integer id;

    @Column(name = "evd_puntaje")
    private Integer puntaje;

    @ManyToOne
    @JoinColumn(name = "evd_indicador")
    private IndicadorJPAModel indicador;

}
