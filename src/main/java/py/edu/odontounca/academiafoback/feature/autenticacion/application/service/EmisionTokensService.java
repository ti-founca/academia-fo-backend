package py.edu.odontounca.academiafoback.feature.autenticacion.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.ProveedorToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.TokensInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.mapper.AutenticacionResultMapper;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.RefreshToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.RefreshTokenRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EmisionTokensService {
    private final ProveedorToken proveedorToken;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AutenticacionResultMapper mapper;

    /** Genera un par access/refresh y registra el refresh token en la BD. */
    public TokensInfo emitir(Usuario usuario, Instant ahora){
        ProveedorToken.TokenEmitido access = this.proveedorToken.generarAccess(usuario, ahora);
        ProveedorToken.TokenEmitido refresh = this.proveedorToken.generarRefresh(usuario, ahora);
        this.refreshTokenRepository.guardar(
                RefreshToken.crear(refresh.jti(), usuario.getId(), refresh.emitidoEn(), refresh.expiraEn())
        );
        return new TokensInfo(
                access.token(),
                access.expiraEn(),
                refresh.token(),
                refresh.expiraEn(),
                this.mapper.toInfo(usuario)
        );
    }
}
