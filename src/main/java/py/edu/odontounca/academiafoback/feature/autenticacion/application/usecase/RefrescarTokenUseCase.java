package py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.ProveedorToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.TokensInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.TokenInvalidoError;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.service.EmisionTokensService;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.RefreshToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.RefreshTokenRepository;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.UsuarioRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RefrescarTokenUseCase {
    private final ProveedorToken proveedorToken;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmisionTokensService emisionTokensService;

    @Transactional
    public Result execute(Query query){
        Instant ahora = Instant.now();
        ProveedorToken.ClaimsRefresh claims = this.proveedorToken.leerRefresh(query.refreshToken())
                .orElseThrow(TokenInvalidoError::new);
        RefreshToken refreshToken = this.refreshTokenRepository.buscarPorJti(claims.jti())
                .filter(rt -> rt.getIdUsuario().equals(claims.idUsuario()))
                .filter(rt -> rt.estaVigente(ahora))
                .orElseThrow(TokenInvalidoError::new);
        Usuario usuario = this.usuarioRepository.buscarPorId(refreshToken.getIdUsuario())
                .orElseThrow(TokenInvalidoError::new);

        // Rotación: el refresh token usado queda revocado y se emite uno nuevo
        refreshToken.revocar(ahora);
        this.refreshTokenRepository.guardar(refreshToken);
        return new Result(this.emisionTokensService.emitir(usuario, ahora));
    }

    public static record Query(String refreshToken){ }
    public static record Result(TokensInfo tokens){ }
}
