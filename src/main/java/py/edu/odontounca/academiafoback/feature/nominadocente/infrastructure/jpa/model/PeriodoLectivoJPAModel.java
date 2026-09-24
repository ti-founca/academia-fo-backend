package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "NominaPeriodoLectivoJPAModel")
@Table(schema = "public", name = "periodoslectivos")
@Getter @Setter @NoArgsConstructor
public class PeriodoLectivoJPAModel {
    @Id
    @Column(name = "pel_id")
    private Integer id;

    @Column(name = "pel_descri", nullable = false, length = 45)
    private String nombre;
}
