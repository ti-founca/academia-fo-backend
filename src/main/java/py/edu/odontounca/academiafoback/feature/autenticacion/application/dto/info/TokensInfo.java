package py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info;

import java.time.Instant;

public record TokensInfo(
        String accessToken,
        Instant accessExpiraEn,
        String refreshToken,
        Instant refreshExpiraEn,
        UsuarioAutenticadoInfo usuario
) {
}
