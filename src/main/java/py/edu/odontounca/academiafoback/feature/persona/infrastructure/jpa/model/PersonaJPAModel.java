package py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "personas")
@Getter @Setter @NoArgsConstructor
public class PersonaJPAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombres;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellidos;

    @Column(name = "nroci", length = 15, nullable = false)
    private String ci;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    @Column(name = "ecivil", length = 1)
    private String estadoCivil;

    @Column(name = "fechanac")
    private LocalDate fechaNacimiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipopersona")
    private TipoPersonaJPAModel tipoPersona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idciudad")
    private CiudadJPAModel ciudad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idnacionalidad")
    private NacionalidadJPAModel nacionalidad;

}
