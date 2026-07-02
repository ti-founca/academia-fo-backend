package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "materias")
@Getter @Setter
public class MateriaJPAModel {
    @Id
    @Column(name = "mat_id")
    private Integer id;
    @Column(name = "mat_descri", length = 120, nullable = false)
    private String descripcion;
    @Column(name = "mat_codigo", length = 10)
    private String codigo;
}
