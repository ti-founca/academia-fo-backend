package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "NominaTipoDocenteJPAModel")
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
