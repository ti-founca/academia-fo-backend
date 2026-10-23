package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ReporteCursoJPAModel")
@Table(schema = "public", name = "cursos")
@Getter
@Setter
@NoArgsConstructor
public class CursoJPAModel {
    @Id
    @Column(name = "cur_id")
    private Integer id;

    @Column(name = "cur_descri", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "cur_seme")
    private Integer semestre;

    @Column(name = "cur_anho")
    private Integer anho;

}
