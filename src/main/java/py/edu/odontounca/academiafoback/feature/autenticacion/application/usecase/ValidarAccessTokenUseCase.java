package py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.ProveedorToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.UsuarioAutenticadoInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.TokenInvalidoError;

@Service
@RequiredArgsConstructor
public class ValidarAccessTokenUseCase {
    private final ProveedorToken proveedorToken;

    /** Validación sin estado (firma, expiración y tipo): no consulta la BD en cada request. */
    public Result execute(Query query){
        ProveedorToken.ClaimsAccess claims = this.proveedorToken.leerAccess(query.accessToken())
                .orElseThrow(TokenInvalidoError::new);
        return new Result(new UsuarioAutenticadoInfo(
                claims.idUsuario(),
                claims.nombre(),
                claims.apellido(),
                claims.correo()
        ));
    }

    public static record Query(String accessToken){ }
    public static record Result(UsuarioAutenticadoInfo usuario){ }
}
