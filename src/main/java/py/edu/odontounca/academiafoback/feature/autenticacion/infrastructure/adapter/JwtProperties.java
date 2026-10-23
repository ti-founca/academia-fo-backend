package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.adapter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String secret,
        Duration accessExpiracion,
        Duration refreshExpiracion
) {
}
