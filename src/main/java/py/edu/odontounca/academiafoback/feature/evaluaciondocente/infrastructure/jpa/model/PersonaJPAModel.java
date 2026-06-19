package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "personas")
@Getter @Setter @RequiredArgsConstructor
public class PersonaJPAModel {
    @Id
    @Column(name = "idpersona")
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombres;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellidos;

    @Column(name = "nroci", length = 15, nullable = false)
    private String ci;
}
