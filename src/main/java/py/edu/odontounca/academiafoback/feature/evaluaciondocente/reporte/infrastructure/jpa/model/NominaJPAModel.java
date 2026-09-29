package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ReporteNomina")
@Table(schema = "academico", name = "nomina_docente")
@Getter @Setter @NoArgsConstructor
public class NominaJPAModel {
    @Id
    @Column(name = "nd_id")
    private Integer id;

    @Column(name = "nd_nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "nd_periodo", nullable = false)
    private Integer idPeriodo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ndd_nomina", nullable = false)
    private List<NominaDetalleJPAModel> detalles = new ArrayList<>();
}
