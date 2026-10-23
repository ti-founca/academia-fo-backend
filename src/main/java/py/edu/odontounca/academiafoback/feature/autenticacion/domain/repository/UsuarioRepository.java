package py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository;

import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    List<Usuario> buscarPorCorreo(String correo);
    Optional<Usuario> buscarPorId(Integer id);
}
