package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto;

import java.time.Instant;

public record TokensDTO(
        String tipo,
        String accessToken,
        Instant accessExpiraEn,
        String refreshToken,
        Instant refreshExpiraEn,
        UsuarioAutenticadoDTO usuario
) {
}
