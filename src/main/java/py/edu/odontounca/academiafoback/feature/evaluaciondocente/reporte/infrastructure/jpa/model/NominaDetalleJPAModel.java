package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ReporteNominaDetalle")
@Table(schema = "academico", name = "nomina_docente_detalle")
@Getter
@Setter
@NoArgsConstructor
public class NominaDetalleJPAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ndd_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ndd_docente")
    private PersonaJPAModel docente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ndd_materia")
    private MateriaJPAModel materia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ndd_tipo_docente")
    private TipoDocenteJPAModel tipoDocente;
}
