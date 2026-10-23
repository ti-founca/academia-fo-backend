package py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.ProveedorToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.RefreshTokenRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CerrarSesionUseCase {
    private final ProveedorToken proveedorToken;
    private final RefreshTokenRepository refreshTokenRepository;

    /** Idempotente: un token inválido o ya revocado no produce error. */
    @Transactional
    public Result execute(Query query){
        Instant ahora = Instant.now();
        this.proveedorToken.leerRefresh(query.refreshToken())
                .flatMap(claims -> this.refreshTokenRepository.buscarPorJti(claims.jti()))
                .filter(rt -> rt.estaVigente(ahora))
                .ifPresent(rt -> {
                    rt.revocar(ahora);
                    this.refreshTokenRepository.guardar(rt);
                });
        return new Result();
    }

    public static record Query(String refreshToken){ }
    public static record Result(){ }
}
