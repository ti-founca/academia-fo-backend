package py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "tipospersonas")
@Getter @Setter @NoArgsConstructor
public class TipoPersonaJPAModel {
    @Id
    @Column(name = "idtipopersona")
    private Integer id;

    @Column(name = "descripcion", length = 45, nullable = false)
    private String descripcion;
}
