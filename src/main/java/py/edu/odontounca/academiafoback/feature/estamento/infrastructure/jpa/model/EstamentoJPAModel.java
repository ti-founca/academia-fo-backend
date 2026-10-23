package py.edu.odontounca.academiafoback.feature.estamento.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "evaluacion", name = "estamento")
@Getter @Setter
public class EstamentoJPAModel {
    @Id
    private Integer id;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column(name = "porcentaje_peso")
    private String porcentajePeso;
}
