package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "config", name = "refresh_token")
@Getter @Setter
public class RefreshTokenJPAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "jti", nullable = false, unique = true)
    private UUID jti;

    @Column(name = "idusuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "emitido_en", nullable = false)
    private Instant emitidoEn;

    @Column(name = "expira_en", nullable = false)
    private Instant expiraEn;

    @Column(name = "revocado_en")
    private Instant revocadoEn;
}
