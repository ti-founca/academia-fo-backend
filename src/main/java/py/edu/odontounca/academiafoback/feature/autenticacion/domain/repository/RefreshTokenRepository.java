package py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository;

import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {
    RefreshToken guardar(RefreshToken refreshToken);
    Optional<RefreshToken> buscarPorJti(UUID jti);
}
