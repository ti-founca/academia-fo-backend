package py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "nacionalidades")
@Getter @Setter @NoArgsConstructor
public class NacionalidadJPAModel {
    @Id
    @Column(name = "idnacionalidad")
    private Integer id;

    @Column(name = "descripcion", length = 45, nullable = false)
    private String descripcion;
}
