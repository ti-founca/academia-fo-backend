package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.adapter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.ProveedorToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@EnableConfigurationProperties(JwtProperties.class)
public class JjwtProveedorToken implements ProveedorToken {
    private static final String CLAIM_TIPO = "tipo";
    private static final String TIPO_ACCESS = "access";
    private static final String TIPO_REFRESH = "refresh";

    private final SecretKey clave;
    private final Duration accessExpiracion;
    private final Duration refreshExpiracion;

    public JjwtProveedorToken(JwtProperties properties) {
        if(properties.secret() == null || properties.secret().getBytes(StandardCharsets.UTF_8).length < 32){
            throw new IllegalStateException("jwt.secret (JWT_SECRET) debe tener al menos 32 bytes");
        }
        this.clave = Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8));
        this.accessExpiracion = properties.accessExpiracion();
        this.refreshExpiracion = properties.refreshExpiracion();
    }

    @Override
    public TokenEmitido generarAccess(Usuario usuario, Instant ahora) {
        UUID jti = UUID.randomUUID();
        Instant expiraEn = ahora.plus(this.accessExpiracion);
        String token = Jwts.builder()
                .id(jti.toString())
                .subject(usuario.getId().toString())
                .claim(CLAIM_TIPO, TIPO_ACCESS)
                .claim("correo", usuario.getCorreo())
                .claim("nombre", usuario.getNombre())
                .claim("apellido", usuario.getApellido())
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(expiraEn))
                .signWith(this.clave)
                .compact();
        return new TokenEmitido(token, jti, ahora, expiraEn);
    }

    @Override
    public TokenEmitido generarRefresh(Usuario usuario, Instant ahora) {
        UUID jti = UUID.randomUUID();
        Instant expiraEn = ahora.plus(this.refreshExpiracion);
        String token = Jwts.builder()
                .id(jti.toString())
                .subject(usuario.getId().toString())
                .claim(CLAIM_TIPO, TIPO_REFRESH)
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(expiraEn))
                .signWith(this.clave)
                .compact();
        return new TokenEmitido(token, jti, ahora, expiraEn);
    }

    @Override
    public Optional<ClaimsAccess> leerAccess(String token) {
        return this.parsear(token, TIPO_ACCESS, claims -> new ClaimsAccess(
                UUID.fromString(claims.getId()),
                Integer.valueOf(claims.getSubject()),
                claims.get("correo", String.class),
                claims.get("nombre", String.class),
                claims.get("apellido", String.class)
        ));
    }

    @Override
    public Optional<ClaimsRefresh> leerRefresh(String token) {
        return this.parsear(token, TIPO_REFRESH, claims -> new ClaimsRefresh(
                UUID.fromString(claims.getId()),
                Integer.valueOf(claims.getSubject())
        ));
    }

    /** Valida firma, expiración y que el claim «tipo» coincida con el esperado; vacío si algo falla. */
    private <T> Optional<T> parsear(String token, String tipoEsperado, Function<Claims, T> mapeo) {
        if(token == null || token.isBlank()) return Optional.empty();
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(this.clave)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            if(!tipoEsperado.equals(claims.get(CLAIM_TIPO, String.class))) return Optional.empty();
            return Optional.of(mapeo.apply(claims));
        } catch (JwtException | IllegalArgumentException | NullPointerException ex) {
            return Optional.empty();
        }
    }
}
