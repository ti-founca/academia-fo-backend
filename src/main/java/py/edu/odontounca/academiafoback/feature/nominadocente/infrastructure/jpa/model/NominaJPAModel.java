package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "academico", name = "nomina_docente")
@Getter @Setter @NoArgsConstructor
public class NominaJPAModel {
    @Id
    @Column(name = "nd_id")
    private Integer id;

    @Column(name = "nd_nombre", nullable = false, length = 255)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nd_periodo")
    private PeriodoLectivoJPAModel periodo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nd_plan")
    private PlanCurricularJPAModel planCurricularJPAModel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ndd_nomina", nullable = false)
    private List<NominaDetalleJPAModel> detalles = new ArrayList<>();
}
