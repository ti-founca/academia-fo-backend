package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "plan_curricular")
@Getter
@Setter
@NoArgsConstructor
public class PlanCurricularJPAModel {
    @Id
    @Column(name = "plc_id")
    private Integer id;

    @Column(name = "plc_descri", nullable = false, length = 45)
    private String nombre;
}
