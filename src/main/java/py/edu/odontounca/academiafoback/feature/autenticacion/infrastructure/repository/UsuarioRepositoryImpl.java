package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.UsuarioRepository;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.mapper.UsuarioJPAMapper;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.repository.UsuarioJPARepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioJPARepository jpaRepository;
    private final UsuarioJPAMapper mapper;

    @Override
    public List<Usuario> buscarPorCorreo(String correo) {
        return this.jpaRepository.findAllByCorreoIgnoreCaseOrderByIdAsc(correo).stream()
                .map(this.mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
