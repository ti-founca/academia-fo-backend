package py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.CifradorClave;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.TokensInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.CredencialesInvalidasError;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.service.EmisionTokensService;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.UsuarioRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IniciarSesionUseCase {
    private final UsuarioRepository usuarioRepository;
    private final CifradorClave cifradorClave;
    private final EmisionTokensService emisionTokensService;

    @Transactional
    public Result execute(Query query){
        if(query.correo() == null || query.correo().isBlank() || query.clave() == null || query.clave().isEmpty()){
            throw new CredencialesInvalidasError();
        }
        List<Usuario> candidatos = this.usuarioRepository.buscarPorCorreo(query.correo().trim());
        if(candidatos.isEmpty()){
            this.cifradorClave.coincide(query.clave(), null);
            throw new CredencialesInvalidasError();
        }
        // El correo puede estar repetido: se autentica el primer usuario cuya clave coincida
        Usuario usuario = candidatos.stream()
                .filter(u -> this.cifradorClave.coincide(query.clave(), u.getClaveCifrada()))
                .findFirst()
                .orElseThrow(CredencialesInvalidasError::new);
        return new Result(this.emisionTokensService.emitir(usuario, Instant.now()));
    }

    public static record Query(String correo, String clave){ }
    public static record Result(TokensInfo tokens){ }
}
