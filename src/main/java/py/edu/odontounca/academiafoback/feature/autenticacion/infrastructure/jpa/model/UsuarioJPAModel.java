package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

// Solo lectura: la columna «clave» (texto plano) no se mapea a propósito
@Entity
@Immutable
@Table(schema = "config", name = "usuarios")
@Getter @Setter
public class UsuarioJPAModel {
    @Id
    @Column(name = "idusuario")
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "apellido", length = 45)
    private String apellido;

    @Column(name = "correo", length = 90)
    private String correo;

    @Column(name = "clave_cifrada", length = 60)
    private String claveCifrada;
}
