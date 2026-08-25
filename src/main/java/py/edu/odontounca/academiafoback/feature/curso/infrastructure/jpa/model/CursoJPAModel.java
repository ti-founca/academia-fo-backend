package py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "cursos")
@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CursoJPAModel {
    @Id
    @Column(name = "cur_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "cur_descri", nullable = false)
    private String descripcion;

    @Column(name = "cur_seme")
    private Integer nroSemestre;

    @Column(name = "cur_sid")
    private Integer ordenSemestre;

    @Column(name = "cur_anho")
    private Integer anho;
}
