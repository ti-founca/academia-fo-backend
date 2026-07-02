package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "academico", name = "tipo_docente")
@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoDocenteJPAModel {
    @Id
    @Column(name = "td_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "td_nombre")
    private String nombre;
}
