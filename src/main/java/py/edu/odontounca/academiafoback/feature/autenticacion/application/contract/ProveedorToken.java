package py.edu.odontounca.academiafoback.feature.autenticacion.application.contract;

import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface ProveedorToken {
    TokenEmitido generarAccess(Usuario usuario, Instant ahora);
    TokenEmitido generarRefresh(Usuario usuario, Instant ahora);
    /** Valida firma, expiración y tipo; vacío si el token no es un refresh token válido. */
    Optional<ClaimsRefresh> leerRefresh(String token);

    record TokenEmitido(String token, UUID jti, Instant emitidoEn, Instant expiraEn){ }
    record ClaimsRefresh(UUID jti, Integer idUsuario){ }
}
